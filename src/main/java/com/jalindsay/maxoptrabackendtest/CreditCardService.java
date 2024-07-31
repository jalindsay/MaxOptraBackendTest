package com.jalindsay.maxoptrabackendtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The CreditCardService class is responsible for handling the business logic
 * TODO: create custom exceptions for invalid card numbers
 */
@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard saveCreditCard(CreditCard creditCard) {

        String cardNumber = creditCard.getCardNumber();
        if (cardNumber.length() < 15 || cardNumber.length() > 16) {
            throw new IllegalArgumentException("Card number must be 15 or 16 digits long");
        }

        if (!LuhnCheck(cardNumber)) {
            throw new IllegalArgumentException("Card number failed Luhn check");
        }

        return creditCardRepository.saveCreditCard(creditCard);
    }

    public boolean LuhnCheck(String cardNumber) {
        // Luhn Algorithm
        // https://en.wikipedia.org/wiki/Luhn_algorithm
        int n = cardNumber.length();
        int total = 0;
        boolean even = true;
        // iterate from right to left, double every 'even' value
        for (int i = n - 2; i >= 0; i--) {
            int digit = cardNumber.charAt(i) - '0';
            if (digit < 0 || digit > 9) {
                // value may only contain digits
                return false;
            }
            if (even) {
                digit <<= 1; // double value
            }
            even = !even;
            total += digit > 9 ? digit - 9 : digit;
        }
        int checksum = cardNumber.charAt(n - 1) - '0';
        return (total + checksum) % 10 == 0;
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
        if (cardNumber.length() == 15) {
            String obfuscatedCardNumber = "XXXX-XXXXXX-X" + cardNumber.substring(11);
            creditCard.setCardNumber(obfuscatedCardNumber);
        } else if (cardNumber.length() == 16) {
            String obfuscatedCardNumber = "XXXX-XXXX-XXXX-" + cardNumber.substring(12);
            creditCard.setCardNumber(obfuscatedCardNumber);
        }
    }
}
