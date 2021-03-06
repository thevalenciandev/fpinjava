package com.thevalenciandev.fpinjava.chapter2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise2_6Test {

    @Test
    void canHandleMultipleTypes() {
        Function<Long, Double> f = a -> a + 2.0;
        Function<Double, Integer> g = a -> (int) (a * 3);

        assertEquals(9, Function.<Long, Double, Integer>higherAndThen().apply(f).apply(g).apply(1L));
    }

}