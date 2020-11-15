package com.embosfer.fpinjava.chapter2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise2_6Test {

    @Test
    void canHandleMultipleTypes() {
        Function<Long, Double> f = a -> a + 2.0;
        Function<Double, Integer> g = a -> (int) (a * 3);

        assertEquals(9, Exercise2_6.<Long, Double, Integer>higherAndThen().apply(f).apply(g).apply(1L));
    }

}