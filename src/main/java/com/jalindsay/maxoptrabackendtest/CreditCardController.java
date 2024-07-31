package com.jalindsay.maxoptrabackendtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The CreditCardController class is responsible for handling the HTTP requests
 * and responses
 */
@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/")
    public String home() {
        return "GET REQUEST DEFAULT PLACEHOLDER";
    }

    /**
     * Hitting this endpoint will populate the list of credit cards
     * with some dummy data
     */
    @GetMapping("/populateCards")
    public void populateCards() {

        List<CreditCard> creditCards = new ArrayList<>();

        CreditCard creditCard1 = new CreditCard();
        creditCard1.setBankName("Natwest");
        creditCard1.setCardNumber("5555555555554444");
        creditCard1.setExpiryDate(LocalDate.of(2025, 12, 31));
        creditCards.add(creditCard1);

        CreditCard creditCard2 = new CreditCard();
        creditCard2.setBankName("Nationwide");
        creditCard2.setCardNumber("4111111111111111");
        creditCard2.setExpiryDate(LocalDate.of(2028, 12, 31));
        creditCards.add(creditCard2);

        CreditCard creditCard3 = new CreditCard();
        creditCard3.setBankName("Lloyds");
        creditCard3.setCardNumber("4111111111111111");
        creditCard3.setExpiryDate(LocalDate.of(2026, 12, 31));
        creditCards.add(creditCard3);

        creditCards.forEach(creditCardService::saveCreditCard);
    }

    @GetMapping("/getCreditCards")
    public List<CreditCard> getCreditCard() {

        return creditCardService.listAllCreditCards();
    }

    @PostMapping("/submitCreditCard")
    public ResponseEntity<?> submitCreditCard(@RequestBody CreditCard creditCard) {

        try {
            return new ResponseEntity<>(creditCardService.saveCreditCard(creditCard), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("exception: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}