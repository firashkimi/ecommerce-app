package com.ideacop.ecommerce.backend.infraestructure.rest;

import com.ideacop.ecommerce.backend.application.OrderService;
import com.ideacop.ecommerce.backend.domain.model.Order;
import com.ideacop.ecommerce.backend.domain.model.OrderState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order){
        if(order.getOrderState().toString().equals(OrderState.CACELLED.toString())) {
            order.setOrderState(OrderState.CACELLED);
        } else {
            order.setOrderState(OrderState.CONFIRMED);
        }

        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @PostMapping("/update/state/order")
    public ResponseEntity<HttpStatus> updateStateById(@RequestParam Integer id, @RequestParam String state) {
        orderService.updateStateById(id, state);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<Iterable<Order>> findByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.findByUserId(id));
    }
}
