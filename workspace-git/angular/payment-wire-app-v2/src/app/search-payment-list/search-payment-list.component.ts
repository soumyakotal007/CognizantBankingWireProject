import { Component, OnInit } from '@angular/core';
import {PaymentSearch} from '../model/payment.search';
import { FormGroup, FormControl } from '@angular/forms';
import {PaymentSearchResponse} from '../model/payment.search.response';
import {PaymentSearchService} from '../service/payment-search.service';

@Component({
  selector: 'app-search-payment-list',
  templateUrl: './search-payment-list.component.html',
  styleUrls: ['./search-payment-list.component.css'],
  providers:[PaymentSearchService]
})
export class SearchPaymentListComponent implements OnInit {

  paymentSearchResponse: PaymentSearchResponse;
  paymentSearchCriteria: PaymentSearch;
  pageSize: number;
  prev: boolean;
  next: boolean;
  searchForm: FormGroup;

  ngOnInit() {      
    this.paymentSearchCriteria = new PaymentSearch(); 
    this.searchForm = new FormGroup({
      "transactionId": new FormControl(0),
      "customerAccountId":  new FormControl(0),
      "transactionStatus":  new FormControl(""),
      "fromDate":  new FormControl(null),
      "toDate":  new FormControl(null)
    });
  }

  constructor(private paymentSearchService: PaymentSearchService) {
    
   }
 

  searchPayment(){
    console.log(this.paymentSearchCriteria); 
    
        this.paymentSearchService
        .searchPayments(this.paymentSearchCriteria)
        .subscribe(data => {
          console.log(data);
          this.paymentSearchResponse = data;
      },
      error => {
        this.paymentSearchResponse = new PaymentSearchResponse();
        this.paymentSearchResponse.responseCode=500;
        this.paymentSearchResponse.responseMessage="Error in calling Payment search service";
      }); 
  }

  setPageNumber(){
    if(this.paymentSearchResponse && this.paymentSearchResponse.totalPage != 0 
      && this.paymentSearchResponse.totalPage != this.paymentSearchCriteria.pageNo){
      if(this.prev){
        if(this.paymentSearchResponse.pageNo > 0){
           this.paymentSearchCriteria.pageNo--;
        }
        else{
          this.prev=false;
        }
      }
      else if(this.next){
        if(this.paymentSearchResponse.totalPage != (this.paymentSearchCriteria.pageNo + 1)){
          this.paymentSearchCriteria.pageNo++;
        }
        else{
          this.next=false;
        }
      }
    }
    else{
      this.prev=false;
      this.next=false;
    }
  }

  getPrevRecords(){ 
    this.prev=true;
    this.setPageNumber();
    if(this.prev){
      this.searchPayment();
    }
  }

  getNextRecords(){
    this.next=true;
    this.setPageNumber();
    if(this.next){
      this.searchPayment();
    }
  }

  onSubmit(){
    this.paymentSearchCriteria = this.searchForm.value;
    this.paymentSearchCriteria.pageNo=0;
    this.paymentSearchCriteria.pageSize=3;
    this.searchPayment();
    console.log(this.searchForm);
  }

}
