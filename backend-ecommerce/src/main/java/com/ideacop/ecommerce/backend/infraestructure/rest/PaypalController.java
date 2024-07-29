package com.ideacop.ecommerce.backend.infraestructure.rest;

import com.ideacop.ecommerce.backend.domain.model.DataPayment;
import com.ideacop.ecommerce.backend.domain.model.URLPaypalResponse;
import com.ideacop.ecommerce.backend.infraestructure.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
@RequestMapping("/api/v1/payments")
public class PaypalController {
    private final PaypalService paypalService;
    private final String SUCCESS_URL= "http://localhost:3000/api/v1/payments/success";
    private final String CANCEL_URL= "http://localhost:3000/api/v1/payments/cancel";

    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }


    @PostMapping
    public URLPaypalResponse createPayment(@RequestBody DataPayment dataPayment) {
        try {
            Payment payment= paypalService.createPayment(
                    Double.valueOf(dataPayment.getAmount()),
                    dataPayment.getCurrency(),
                    dataPayment.getMethod(),
                    "SALE",
                    dataPayment.getDescription(),
                    CANCEL_URL,
                    SUCCESS_URL
            );
            for(Links links: payment.getLinks()) {
                if(links.getRel().equals("approval_url")){
                    return new URLPaypalResponse(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            log.error("Error al crear el pago con paypal {}", e.getMessage());
            throw new RuntimeException(e);
        }

        return new URLPaypalResponse("http://localhost:3200");
    }

    @GetMapping("/success")
    public RedirectView paymentSuccess(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        try {
            Payment payment= paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                return new RedirectView("http://localhost:3200/payment/success");
            }
        } catch (PayPalRESTException e) {
            log.error("Error al ejecutar el pago con paypal {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return new RedirectView("http://localhost:3200");
    }

    @GetMapping("/cancel")
    public RedirectView paymentCancel(){
        return new RedirectView("http://localhost:3200");
    }
}
