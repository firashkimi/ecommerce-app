import { Injectable } from '@angular/core';
import { API_URL } from '../common/app.constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  private endpoint = "/api/v1/home"

  constructor(private http: HttpClient) { }

  getProducts():Observable<Product[]> {
    return this.http.get<Product[]>(API_URL + this.endpoint);
  }

  getProductById(id: number):Observable<Product> {
    return this.http.get<Product>(API_URL + this.endpoint + "/product/" + id);
  }
}
