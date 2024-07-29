import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { API_URL } from '../common/app.constants';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private endpoint = "/api/v1/admin/products"

  constructor(private http: HttpClient, private header: HeaderService) { }

  getProducts():Observable<Product[]> {
    return this.http.get<Product[]>(API_URL + this.endpoint, { headers: this.header.headers });
  }

  createProduct(product: Product):Observable<any> {
    const formData = new FormData();

    formData.append('product', new Blob([JSON.stringify({
      id: product.id,
      name: product.name,
      code: product.code,
      description: product.description,
      urlImage: product.urlImage,
      price: product.price,
      userId: 1,//product.userId,
      categoryId: product.categoryId
    })], { type: "application/json" }));
    if(product.image) {
      formData.append('image', product.image);
    } else {
      formData.append('image', "");
    }


    return this.http.post<Product>(API_URL + this.endpoint, formData, { headers: this.header.headers });
  }

  deleteProductById(id: number):Observable<any> {
    return this.http.delete(API_URL + this.endpoint + "/" + id, { headers: this.header.headers });
  }

  getProductById(id: number):Observable<Product> {
    return this.http.get<Product>(API_URL + this.endpoint + "/" + id, { headers: this.header.headers });
  }
}
