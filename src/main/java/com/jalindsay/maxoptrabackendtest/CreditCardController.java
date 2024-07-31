package com.jalindsay.maxoptrabackendtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/")
    public String home() {
        return "GET REQUEST DEFAULT PLACEHOLDER";
    }

    @GetMapping("/getCreditCards")
    public List<CreditCard> getCreditCard() {

        // List<CreditCard> creditCards = new ArrayList<>();
        List<CreditCard> creditCards = creditCardService.listAllCreditCards();

//        CreditCard creditCard = new CreditCard();
//        creditCard.setBankName("Natwest");
//        creditCard.setCardNumber("1234-5678-9101-1121");
//        creditCard.setExpiryDate(LocalDate.of(2025, 12, 31));

//        creditCards.add(creditCard);

        return creditCards;
    }

    @PostMapping("/submitCreditCard")
    public CreditCard submitCreditCard(@RequestBody CreditCard creditCard) {

        CreditCard savedCreditCard = creditCardService.saveCreditCard(creditCard);

        return savedCreditCard;
    }
}