import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {PaymentAuthorizationRequest} from '../model/payment.authorization.request';
import {PaymentAuthorizationResponse} from '../model/payment.authorization.response';
import {AppConstant} from '../util/app-constant';

@Injectable({
  providedIn: 'root'
})
export class PaymentAuthorizeService {

  constructor(private http: HttpClient) { }

  authorizeMerchant(paymentAuthorizeRequest: PaymentAuthorizationRequest) {                
    const headers = { 
           'Authorization': 'Basic dXNlcjE6cGFzc3dvcmQx', 
           'PAYMENT-AUTHORIZATION-VERSION': 'v1.0' ,
           'Content-Type': 'application/json'
          };
    const body = JSON.stringify(paymentAuthorizeRequest);

    return this.http.post<PaymentAuthorizationResponse>(AppConstant.authorizeServiceUrl,
    body, { headers });
}
}
