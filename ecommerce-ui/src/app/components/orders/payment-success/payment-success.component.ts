import { Component, OnInit } from '@angular/core';
import { OrderState } from 'src/app/common/order-state';
import { OrderService } from 'src/app/services/order.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';

@Component({
  selector: 'app-payment-success',
  templateUrl: './payment-success.component.html',
  styleUrls: ['./payment-success.component.css']
})
export class PaymentSuccessComponent implements OnInit {

  constructor(private orderService: OrderService, private sesionStorage: SessionStorageService){}

  ngOnInit(): void {
    console.log("Order a actualizar {}", this.sesionStorage.getItem('order'))
    let order = this.sesionStorage.getItem('order')

    let formData= new FormData()
    formData.append('id', order.id)
    formData.append('state', OrderState.CONFIRMED.toString())

    this.orderService.updateOrder(formData).subscribe({
      next: response => console.log("Update order result {}",response)
    })

  }

}
