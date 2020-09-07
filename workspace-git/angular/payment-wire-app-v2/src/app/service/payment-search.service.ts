import { Injectable } from '@angular/core';
import { HttpClient ,HttpErrorResponse} from '@angular/common/http';
import {PaymentSearch} from '../model/payment.search';
import {PaymentSearchResponse} from '../model/payment.search.response';
import {AppConstant} from '../util/app-constant';
import { catchError } from 'rxjs/operators';
import {  throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PaymentSearchService {

    constructor(private http: HttpClient) { }

    searchPayments(paymentSearchCriteria: PaymentSearch) {                
      const headers = { 
             'Authorization': 'Basic dXNlcjE6cGFzc3dvcmQx', 
             'PAYMENT-INITIATION-VERSION': 'v1.0' ,
             'Content-Type': 'application/json'
            };
      const body = JSON.stringify(paymentSearchCriteria);

      return this.http.post<PaymentSearchResponse>(AppConstant.searchServiceUrl,
      body, { headers }).pipe(catchError(this.handleError));
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
