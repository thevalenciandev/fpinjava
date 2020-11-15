package com.thevalenciandev.fpinjava.chapter1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DonutShopTest {

    @Test
    void canBuyOneDonut() {
        CreditCard creditCard = new CreditCard();
        Tuple<Donut, Payment> purchase = DonutShop.buyDonut(creditCard);
        assertEquals(2, purchase._2.amount);
        assertEquals(creditCard, purchase._2.creditCard);
    }

}