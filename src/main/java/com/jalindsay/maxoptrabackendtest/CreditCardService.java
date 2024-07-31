package com.jalindsay.maxoptrabackendtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardRepository.saveCreditCard(creditCard);
    }

    public List<CreditCard> listAllCreditCards() {
        List<CreditCard> creditCards = creditCardRepository.listAllCreditCards();

        // Obfuscate each credit card number
        for (CreditCard creditCard : creditCards) {
            obfuscateCreditCard(creditCard);
        }

        return creditCards;
    }

    // Obfuscate the credit card number
    private void obfuscateCreditCard(CreditCard creditCard) {
        String cardNumber = creditCard.getCardNumber();
        String obfuscatedCardNumber = "XXXX-XXXX-XXXX-" + cardNumber.substring(15);
        creditCard.setCardNumber(obfuscatedCardNumber);
    }
}
