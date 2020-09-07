import { Component, OnInit, Input } from '@angular/core';
import {PaymentSearchResponse} from '../model/payment.search.response';

@Component({
  selector: 'app-unpaid-payment-list',
  templateUrl: './unpaid-payment-list.component.html',
  styleUrls: ['./unpaid-payment-list.component.css']
})
export class UnpaidPaymentListComponent implements OnInit {

  @Input() searchResponse: PaymentSearchResponse;

  constructor() { }

  ngOnInit(): void {
  }

}
