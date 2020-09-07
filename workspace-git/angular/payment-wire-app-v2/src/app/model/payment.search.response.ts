import {TransactionDetails} from '../model/transaction.model'

export class PaymentSearchResponse{
    responseCode: number;
    responseMessage: string;
    pageNo: number;
    pageSize: number;
    totalPage: number;
    paymentList: TransactionDetails[];
}