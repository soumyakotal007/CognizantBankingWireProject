import { Injectable } from '@angular/core';
import {AppConstant} from '../util/app-constant';
import { HttpClient } from '@angular/common/http';
import {PaymentVerificationRequest} from '../model/payment.verification.request';
import {PaymentVerifitionResponse} from '../model/payment.verification.response';

@Injectable({
  providedIn: 'root'
})
export class PaymentVerifyService {

  constructor(private http: HttpClient) { }

    verifyCustomer(paymentVerifyRequest: PaymentVerificationRequest) {                
      const headers = { 
             'Authorization': 'Basic dXNlcjE6cGFzc3dvcmQx', 
             'PAYMENT-VERIFICATION-VERSION': 'v1.0' ,
             'Content-Type': 'application/json'
            };
      const body = JSON.stringify(paymentVerifyRequest);

      return this.http.post<PaymentVerifitionResponse>(AppConstant.verificationServiceUrl,
      body, { headers });
  }
}
