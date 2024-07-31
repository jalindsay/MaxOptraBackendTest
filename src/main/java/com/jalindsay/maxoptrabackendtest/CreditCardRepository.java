package com.jalindsay.maxoptrabackendtest;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * If this repo connected to a database it would extend JpaRepository<CreditCard, Long>
 * Instead we are using a List to store the CreditCard objects
 */
@Repository
public class CreditCardRepository {

    private final List<CreditCard> creditCards = new ArrayList<>();

    public List<CreditCard> listAllCreditCards() {
        return creditCards;
    }

    public CreditCard saveCreditCard(CreditCard creditCard) {
        creditCards.add(creditCard);
        return creditCard;
    }

}
