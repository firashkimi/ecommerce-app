import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Order } from 'src/app/common/order';
import { OrderProduct } from 'src/app/common/order-product';
import { HomeService } from 'src/app/services/home.service';
import { OrderService } from 'src/app/services/order.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-order-list',
  templateUrl: './admin-order-list.component.html',
  styleUrls: ['./admin-order-list.component.css']
})
export class AdminOrderListComponent implements OnInit {

  orders: Order[] = [];
  nombreUsuario: string="";
  orderProducts: OrderProduct[] = []
  order: any
  nombreProducto: string=""

  constructor(private orderService: OrderService, private sesionStorage: SessionStorageService, private homeService: HomeService, private toastr: ToastrService, private userService: UserService){}

  ngOnInit(): void {
    this.listOrders()
  }

  listOrders(){
    this.orderService.getOrders().subscribe({
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

  getItemClass(item: any): string{
    return item?.orderState === "CONFIRMED" ? 'table-success' : 'table-secondary';
  }

}
