import {TransactionDetails} from './transaction.model';

export class PaymentInitiationResponse{
    responseCode : number;
    responseTime : number;
    clientMessage : string;
    wireData: TransactionDetails;
}