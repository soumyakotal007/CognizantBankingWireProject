import { Injectable } from '@angular/core';
import { HttpClient ,HttpErrorResponse} from '@angular/common/http';
import {AppConstant} from '../util/app-constant';
import {PaymentInitiationRequest} from '../model/payment.initiation.request';
import {PaymentInitiationResponse} from '../model/payment.intiation.response';
import { catchError } from 'rxjs/operators';
import {  throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentInitiationService {

  constructor(private http: HttpClient) { }

  initiatePayments(paymentInitiationRequest: PaymentInitiationRequest) {                
    const headers = { 
           'Authorization': 'Basic dXNlcjE6cGFzc3dvcmQx', 
           'PAYMENT-INITIATION-VERSION': 'v1.0' ,
           'Content-Type': 'application/json'
          };
    const body = JSON.stringify(paymentInitiationRequest);

    return this.http.post<PaymentInitiationResponse>(AppConstant.initiationServiceUrl,
    body, { headers }).pipe(catchError(this.handleError))
    ;
}

handleError(error: HttpErrorResponse) {
  let errorMessage = 'Unknown error!';
  if (error.error instanceof ErrorEvent) {
    // Client-side errors
    errorMessage = `Error: ${error.error.message}`;
  } else {
    // Server-side errors
    errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
  }
  //window.alert(errorMessage);
  return throwError(errorMessage);
}
}
