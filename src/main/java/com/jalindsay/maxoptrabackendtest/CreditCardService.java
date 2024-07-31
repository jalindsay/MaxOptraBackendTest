package com.jalindsay.maxoptrabackendtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard saveCreditCard(CreditCard creditCard) {

        String cardNumber = creditCard.getCardNumber();
        if (cardNumber.length() < 15 || cardNumber.length() > 16) {
            throw new IllegalArgumentException("Card number must be 15 or 16 digits long");
        }

        // TODO: Do luhn check here
        // TODO: Create custom exception for invalid card number

        return creditCardRepository.saveCreditCard(creditCard);
    }

    public void LuhnCheck(String cardNumber) {
        // Luhn Algorithm
        // https://en.wikipedia.org/wiki/Luhn_algorithm
    }

    public List<CreditCard> listAllCreditCards() {
        List<CreditCard> creditCards = new ArrayList<>(creditCardRepository.listAllCreditCards());

        // Copy the list of Credit Cards so that the original list is not modified
        List<CreditCard> creditCardsCopy = new ArrayList<>(creditCards.size());
        for (CreditCard creditCard : creditCards) {
            creditCardsCopy.add(new CreditCard(creditCard));
        }
        // Sort Credit Cards by Expiry Date in descending order
        creditCardsCopy.sort((c1, c2) -> c2.getExpiryDate().compareTo(c1.getExpiryDate()));

        // Obfuscate each credit card number
        for (CreditCard creditCardCopy : creditCardsCopy) {
            obfuscateCreditCard(creditCardCopy);
        }

        return creditCardsCopy;
    }

    // Obfuscate the credit card number
    private void obfuscateCreditCard(CreditCard creditCard) {
        String cardNumber = creditCard.getCardNumber();
        String obfuscatedCardNumber = "XXXX-XXXX-XXXX-" + cardNumber.substring(12);
        creditCard.setCardNumber(obfuscatedCardNumber);
    }
}
