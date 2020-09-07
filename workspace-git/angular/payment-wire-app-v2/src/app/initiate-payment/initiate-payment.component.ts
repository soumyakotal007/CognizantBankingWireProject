import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl, Validators ,FormBuilder} from '@angular/forms';
import {PaymentInitiationRequest} from '../model/payment.initiation.request'; 
import {PaymentInitiationResponse} from '../model/payment.intiation.response';
import {PaymentInitiationService} from '../service/payment-initiation.service';

@Component({
  selector: 'app-initiate-payment',
  templateUrl: './initiate-payment.component.html',
  styleUrls: ['./initiate-payment.component.css'],
  providers: [PaymentInitiationService]
})
export class InitiatePaymentComponent implements OnInit {
  initiateForm : FormGroup;
  initiationRequest : PaymentInitiationRequest;
  initiationResponse: PaymentInitiationResponse;
  builder : FormBuilder;
  constructor(private initiationService : PaymentInitiationService) { }

  ngOnInit(): void {
    this.initiateForm = new FormGroup({
      "customerId": new FormControl(null,[Validators.required,
        Validators.minLength(3)]),
      "customerBankId": new FormControl(null,[Validators.required,Validators.minLength(3), 
        Validators.maxLength(3)]),
      "customerAccount": new FormControl(null,[Validators.required,Validators.minLength(12), 
        Validators.maxLength(12)]),
      "merchantAccount": new FormControl(null,[Validators.required,Validators.minLength(12), 
        Validators.maxLength(12)]),
      "merchantId": new FormControl(null,[Validators.required,Validators.minLength(3), 
        Validators.maxLength(3)]),
      "merchantBankId": new FormControl(null,[Validators.required,Validators.minLength(3), 
        Validators.maxLength(3)]),
      "paymentAmt":  new FormControl(null,[Validators.required])
    });
  }
  resetForm(){
    this.initiateForm.reset({});
  }
  onSubmit(){
    this.initiationRequest=this.initiateForm.value;
    console.log(this.initiateForm);
  
    if(this.initiateForm.valid){
      this.initiationService
      .initiatePayments(this.initiationRequest)
      .subscribe(data => {
        console.log(data);
        this.initiationResponse=data;
        },
        err => {
          console.log(err);
          this.initiationResponse = new PaymentInitiationResponse();
          this.initiationResponse.responseCode=500;
          this.initiationResponse.clientMessage=err;
        }); 
    }
    else{
      this.initiationResponse = new PaymentInitiationResponse();
      this.initiationResponse.responseCode=500;
      this.initiationResponse.clientMessage="Please submit the form with proper input data.";
    }
  }
}
