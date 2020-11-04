package com.embosfer.fpinjava.chapter1;

public final class Payment {

    public final CreditCard creditCard;
    public final int amount;

    public Payment(CreditCard creditCard, int amount) {
        this.creditCard = creditCard;
        this.amount = amount;
    }

    public Payment combine(Payment other) {
        if (other.creditCard == this.creditCard) {
            return new Payment(creditCard, this.amount + other.amount);
        } else {
            throw new IllegalStateException("Cards don't match");
        }
    }
}
