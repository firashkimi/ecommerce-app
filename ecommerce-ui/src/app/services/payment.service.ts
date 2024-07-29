import { Injectable } from '@angular/core';
import { API_URL } from '../common/app.constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UrlPaypalResponse } from '../common/url-paypal-response';
import { DataPayment } from '../common/data-payment';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private endpoint = "/api/v1/payments"

  constructor(private http: HttpClient, private header: HeaderService) { }

  getUrlPaypalPayment(dataPayment: DataPayment):Observable<UrlPaypalResponse>{
    return this.http.post<UrlPaypalResponse>(API_URL + this.endpoint, dataPayment, { headers: this.header.headers });
  }
}
