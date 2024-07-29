import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../common/user';
import { API_URL } from '../common/app.constants';
import { HeaderService } from './header.service';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private endpoint = "/api/v1/users"

  constructor(private http: HttpClient, private header: HeaderService) { }

  getUserById(id:number):Observable<User>{
    return this.http.get<User>(API_URL + this.endpoint + "/" + id, { headers: this.header.headers });
  }

  getUsers():Observable<User[]>{
    return this.http.get<User[]>(API_URL + this.endpoint, { headers: this.header.headers })
  }
}
