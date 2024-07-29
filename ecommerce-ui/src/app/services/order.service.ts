import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../common/order';
import { API_URL } from '../common/app.constants';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private endpoint = "/api/v1/orders"

  constructor(private http: HttpClient, private header: HeaderService) { }

  createOrder(order: Order):Observable<Order>{
    return this.http.post<Order>(API_URL + this.endpoint, order, { headers: this.header.headers })
  }

  updateOrder(formData:any):Observable<any>{
    return this.http.post(API_URL + this.endpoint + "/update/state/order", formData, { headers: this.header.headers })
  }

  getOrderByUser(userId: number):Observable<Order[]> {
    return this.http.get<Order[]>(API_URL + this.endpoint + "/user-id/" + userId, { headers: this.header.headers })
  }

  getOrderById(id: number):Observable<Order> {
    return this.http.get<Order>(API_URL + this.endpoint + "/" + id, { headers: this.header.headers })
  }

  getOrders():Observable<Order[]> {
    return this.http.get<Order[]>(API_URL + this.endpoint, {headers: this.header.headers})
  }


}
