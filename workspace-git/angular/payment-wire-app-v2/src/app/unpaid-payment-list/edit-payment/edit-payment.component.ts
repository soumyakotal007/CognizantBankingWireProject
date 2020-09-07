import { Component, OnInit, Input } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {TransactionDetails} from '../../model/transaction.model';
import {PaymentVerifyService} from '../../service/payment-verify.service';
import {PaymentAuthorizeService} from '../../service/payment-authorize.service';
import {PaymentCompleteService} from '../../service/payment-complete.service';
import {PaymentVerificationRequest} from '../../model/payment.verification.request';
import {PaymentVerifitionResponse} from '../../model/payment.verification.response';
import {PaymentAuthorizationRequest} from '../../model/payment.authorization.request';
import {PaymentAuthorizationResponse} from '../../model/payment.authorization.response';
import {PaymentCompletionRequest} from '../../model/payment.completion.request';
import {PaymentComppletionResponse} from '../../model/payment.completion.response';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-edit-payment',
  templateUrl: './edit-payment.component.html',
  styleUrls: ['./edit-payment.component.css'],
  providers:[PaymentVerifyService,PaymentAuthorizeService,PaymentCompleteService]
})
export class EditPaymentComponent implements OnInit {
  verificationRequest: PaymentVerificationRequest;
  verificationResponse: PaymentVerifitionResponse;
  authorizationRequest: PaymentAuthorizationRequest;
  authorizationResponse: PaymentAuthorizationResponse;
  completionRequest : PaymentCompletionRequest;
  completionResponse : PaymentComppletionResponse;
  responseCode:number = 0;
  responseStatus:string = 'default';

  @Input() paymentDtl : TransactionDetails;
  isAuthorized: boolean = false;
  isVerified: boolean = false;
  isPaymentComlete: boolean = false;
  editForm: FormGroup;

  ngOnInit(): void {
    //console.log(new DatePipe('en').transform(new Date(this.paymentDtl.paymentDueDate), 'dd-MM-yyyy'));
    this.editForm = new FormGroup({
      "customerAccount" : new FormControl(this.paymentDtl.customerAccount),
      "merchantAccount" : new FormControl(this.paymentDtl.merchantAccount),
      "paymentDueDate" :  new FormControl(this.paymentDtl.paymentDueDate),
      "paymentAmt" : new FormControl(this.paymentDtl.paymentAmt),
      "transactionId":new FormControl(this.paymentDtl.transactionId),
      "customerId":new FormControl(this.paymentDtl.customerId),
      "customerBankId":new FormControl(this.paymentDtl.customerBankId),
      "merchantId":new FormControl(this.paymentDtl.merchantId),
      "merchantBankId":new FormControl(this.paymentDtl.merchantBankId),
      "paymentStatus":new FormControl(this.paymentDtl.paymentStatus)
    });
  }
   
 

  closeResult = '';

  constructor(private modalService: NgbModal,
    private verifyService: PaymentVerifyService,
    private authorizeService: PaymentAuthorizeService,
    private completeService: PaymentCompleteService) {}

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
  verifiyCustomer(){
    this.verificationRequest = new PaymentVerificationRequest();
    this.verificationRequest.account=this.paymentDtl.customerAccount;
    this.verificationRequest.bankId=this.paymentDtl.customerBankId;
    this.verificationRequest.customerId=this.paymentDtl.customerId;
    this.verificationRequest.paymentAmount=this.paymentDtl.paymentAmt;
    this.callVerifyService(); 
  }
  
  authorizeMerchant(){
    this.authorizationRequest = new PaymentAuthorizationRequest();
    this.authorizationRequest.account=this.paymentDtl.merchantAccount;
    this.authorizationRequest.bankId=this.paymentDtl.merchantBankId;
    this.authorizationRequest.merchantId=this.paymentDtl.merchantId;
    this.callAuthorizantionService();
  }
  completePayment(){
    this.completionRequest = new PaymentCompletionRequest();
    this.completionRequest.paymentAmount=this.paymentDtl.paymentAmt;
    this.completionRequest.wireID=this.paymentDtl.transactionId;
    this.callCompletePaymentService();
  }

  callCompletePaymentService(){
    this.completeService.completePayment(this.completionRequest)
    .subscribe(data => {
      console.log(data);
      this.completionResponse = data;
      if(this.completionResponse && this.completionResponse.responseCode == 200){
        this.isPaymentComlete = true;
      }
        this.responseCode=this.completionResponse.responseCode;
        this.responseStatus=this.completionResponse.clientMessage;
    });
  }

  callAuthorizantionService(){
    this.authorizeService.authorizeMerchant(this.authorizationRequest)
    .subscribe(data => {
      console.log(data);
      this.authorizationResponse=data;
      if(this.authorizationResponse && this.authorizationResponse.responseCode == 200){
        this.isAuthorized =true;
      }
        this.responseCode=this.authorizationResponse.responseCode;
        this.responseStatus=this.authorizationResponse.clientMessage;
    });
  }

  callVerifyService(){
      this.verifyService
      .verifyCustomer(this.verificationRequest)
      .subscribe(data => {
        console.log(data);
        this.verificationResponse = data;
        if(this.verificationResponse && this.verificationResponse.responseCode == 200){
          this.isVerified=true;
        }
        this.responseCode=this.verificationResponse.responseCode;
        this.responseStatus=this.verificationResponse.clientMessage;
    }); 
  }

  

  modifyCustomer(){
    this.completeService.updatePayment(this.editForm.value)
    .subscribe(data => {
      this.completionResponse=data;
      console.log(data);if(this.completionResponse && this.completionResponse.responseCode == 200){
        this.isPaymentComlete = true;
      }
        this.responseCode=this.completionResponse.responseCode;
        this.responseStatus=this.completionResponse.clientMessage;
    });
  }
}
