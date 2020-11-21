package com.thevalenciandev.fpinjava.chapter2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Exercise2_5Test {

    @Test
    void canUseAnonymousFunctions() {
        Double cosineOfTwo = Function.<Double, Double, Double>higherCompose().apply(x -> Math.PI / 2 - x).apply(Math::sin).apply(2.0);
        assertEquals(0.6614988999692148, cosineOfTwo);
    }

}