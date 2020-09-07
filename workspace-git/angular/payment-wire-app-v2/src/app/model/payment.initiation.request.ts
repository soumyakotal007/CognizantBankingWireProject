export class PaymentInitiationRequest{
    customerId: number;
    customerBankId: number;
    customerAccount: number;
    merchantAccount: number;
    merchantId: number;
    merchantBankId: number;
    paymentAmt: number;
  }