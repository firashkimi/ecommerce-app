import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../common/user';
import { Observable } from 'rxjs';
import { API_URL } from '../common/app.constants';
import { UserDto } from '../common/userdto';
import { Jwtclient } from '../common/jwtclient';
import { UserProfile } from '../common/userprofile';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private endpoint = "/api/v1/security"

  constructor(private http: HttpClient) { }

  register(user: User):Observable<User>{
    return this.http.post<User>(API_URL + this.endpoint + "/register", user);
  }

  updateprofile(user: UserProfile):Observable<any>{
    return this.http.post(API_URL + this.endpoint + "/updateProfile", user);
  }
  login(userDto: UserDto):Observable<Jwtclient> {
    return this.http.post<Jwtclient>(API_URL + this.endpoint + "/login", userDto);
  }
}
