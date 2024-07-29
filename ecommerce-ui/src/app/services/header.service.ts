import { HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionStorageService } from './session-storage.service';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {

  private token: string = "";
  public headers: HttpHeaders= new HttpHeaders;

  constructor(private sesionStorage: SessionStorageService) {
    this.token= this.sesionStorage.getItem('userData')?.token;

    this.headers= new HttpHeaders(
      {
        'Authorization': `${this.token}`
      }
    )
   }
}
