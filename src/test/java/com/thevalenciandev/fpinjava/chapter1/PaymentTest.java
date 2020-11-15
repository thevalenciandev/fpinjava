package com.thevalenciandev.fpinjava.chapter1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void canCombineMultiplePaymentsFromSameCardIntoOne() {
        CreditCard creditCard = new CreditCard();
        Payment p1 = new Payment(creditCard, 1);
        Payment p2 = new Payment(creditCard, 2);

        Payment combined = p1.combine(p2);
        assertEquals(creditCard, combined.creditCard);
        assertEquals(3, combined.amount);
    }

    @Test
    void whenCombiningPaymentsThrowsIfDifferentCardsUsed() {
        Payment p1 = new Payment(new CreditCard(), 1);
        Payment p2 = new Payment(new CreditCard(), 2);

        assertThrows(IllegalStateException.class, () -> p1.combine(p2));
    }

}