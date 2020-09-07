export class AppConstant{
    static searchServiceUrl : string = "http://localhost:8081/search/payment";
    static verificationServiceUrl : string = "http://localhost:8082/verification/customer";
    static authorizeServiceUrl : string = "http://localhost:8083/authorization/merchant";
    static completionServiceUrl : string = "http://localhost:8084/completion/payment";
    static initiationServiceUrl : string = "http://localhost:8081/initiation/payment";
    static updateServiceUrl : string = "http://localhost:8084/update/payment";
}