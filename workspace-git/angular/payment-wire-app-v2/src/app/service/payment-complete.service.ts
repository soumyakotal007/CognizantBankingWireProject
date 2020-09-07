import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {AppConstant} from '../util/app-constant';
import {PaymentCompletionRequest} from '../model/payment.completion.request';
import {PaymentComppletionResponse} from '../model/payment.completion.response';

@Injectable({
  providedIn: 'root'
})
export class PaymentCompleteService {

  constructor(private http: HttpClient) { }

  completePayment(paymentCompletionRequest: PaymentCompletionRequest) {                
    const headers = { 
           'Authorization': 'Basic dXNlcjE6cGFzc3dvcmQx', 
           'PAYMENT-COMPLETION-VERSION': 'v1.0' ,
           'Content-Type': 'application/json'
          };
    const body = JSON.stringify(paymentCompletionRequest);

    return this.http.post<PaymentComppletionResponse>(AppConstant.completionServiceUrl,
    body, { headers });
}

updatePayment(paymentCompletionRequest: String) {                
  const headers = { 
         'Authorization': 'Basic dXNlcjE6cGFzc3dvcmQx', 
         'PAYMENT-COMPLETION-VERSION': 'v1.0' ,
         'Content-Type': 'application/json'
        };

  return this.http.post<PaymentComppletionResponse>(AppConstant.updateServiceUrl,
    paymentCompletionRequest, { headers });
}
}
