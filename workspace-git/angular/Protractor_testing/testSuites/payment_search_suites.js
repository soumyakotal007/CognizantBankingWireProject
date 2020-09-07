describe('Payment search testing', function() {
    let transactionId = "";
    
    it('Initiate Payment - Submit button', function(){
        browser.get('http://localhost:4200/initiatePayment');
        element(by.id('custID')).sendKeys('102');
        element(by.id('customerBankId')).sendKeys('202');
        element(by.id('customerAccount')).sendKeys('212345345675');

        element(by.id('merchantId')).sendKeys('103');
        element(by.id('merchantBankId')).sendKeys('201');
        element(by.id('merchantAccount')).sendKeys('786545345673');

        element(by.id('paymentAmt')).sendKeys('1000');

        element(by.id('submitBtn')).click();

        expect(element(by.id('custID')).getAttribute('value')).toMatch('102');
        expect(element(by.id('paymentAmt')).getAttribute('value')).toMatch('1000');
        expect(element(by.id('wireID')).isPresent());
        transactionId = element(by.id('wireID')).getText();
        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/initiatePayment');
       // browser.sleep(10000)
    }); 

    it('Initiate Payment - Reset button', function(){
        browser.get('http://localhost:4200/initiatePayment');
        
        element(by.id('custID')).sendKeys('102');
        element(by.id('customerBankId')).sendKeys('202');
        element(by.id('customerAccount')).sendKeys('212345345675');

        element(by.id('merchantId')).sendKeys('103');
        element(by.id('merchantBankId')).sendKeys('201');
        element(by.id('merchantAccount')).sendKeys('786545345673');

        element(by.id('paymentAmt')).sendKeys('1000');

        element(by.id('resetBtn')).click();

        expect(element(by.id('custID')).getAttribute('value')).toMatch('');
        expect(element(by.id('paymentAmt')).getAttribute('value')).toMatch('');
        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/initiatePayment');
       // browser.sleep(10000)
    }); 
    
    it('Search with transaction ID', function(){
        browser.get('http://localhost:4200/searchPayment');
        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/searchPayment');
        element(by.id('ex1')).sendKeys(transactionId);
        element(by.id('searchbtn')).click();
        expect(element(by.id('ex1')).getAttribute('value')).toMatch(transactionId);
        //browser.sleep(10000)
    }); 

    it('wrong search url', function(){
        browser.get('http://localhost:4200/searchPaymentWrong');
        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/searchPaymentWrong');
    }); 
});