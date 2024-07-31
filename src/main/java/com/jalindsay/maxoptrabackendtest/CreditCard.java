package com.jalindsay.maxoptrabackendtest;

import java.time.LocalDate;

public class CreditCard {

    private Long id;

    private String bankName;

    private String cardNumber;

    private LocalDate expiryDate;

    // Getters and Setters
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Optionally override toString(), equals(), and hashCode() methods
    public String toString() {
        return "CreditCard{" +
                "bankName='" + bankName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}