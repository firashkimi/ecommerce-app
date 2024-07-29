import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Order } from 'src/app/common/order';
import { OrderProduct } from 'src/app/common/order-product';
import { HomeService } from 'src/app/services/home.service';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit{

  orders: Order[] = [];
  userId: number= 0;
  orderProducts: OrderProduct[] = []
  order: any
  nombreProducto: string=""

  constructor(private orderService: OrderService, private sesionStorage: SessionStorageService, private homeService: HomeService, private toastr: ToastrService){
    this.userId= this.sesionStorage.getItem('userData').id;
  }

  ngOnInit(): void {
    this.listOrdersByUser(this.userId)
  }

  listOrdersByUser(id: number){
    this.orderService.getOrderByUser(id).subscribe({
      next: (data)=> {
        this.orders= data
      },
      error: (error) => {
        if(error.status===403) {
          this.toastr.error("Vous devez être connecté pour voir les données sur la page.")
        }
      }
    })
  }

  verProductosOrden(ordenId: any){
    this.order = this.orders.filter(order => order.id === ordenId)
    this.orderProducts = this.order[0].orderProducts;
    this.homeService.getProductById(this.orderProducts[0].productId).subscribe({
      next: data=> this.nombreProducto= data.name
    })
  }


}
