import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../common/category';
import { API_URL } from '../common/app.constants';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private endpoint = "/api/v1/admin/categories"

  constructor(private http: HttpClient, private header: HeaderService) { }

  getCategories():Observable<Category[]>{
    return this.http.get<Category[]>(API_URL + this.endpoint, { headers: this.header.headers })
  }

  getCategoryById(id: number):Observable<Category> {
    return this.http.get<Category>(API_URL + this.endpoint + "/" + id, { headers: this.header.headers })
  }

  saveCategory(category: Category):Observable<Category>{
    return this.http.post<Category>(API_URL + this.endpoint, category, { headers: this.header.headers })
  }

  deleteCategory(id: number):Observable<any> {
    return this.http.delete(API_URL + this.endpoint + "/" + id, { headers: this.header.headers })
  }
}
