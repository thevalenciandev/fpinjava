package com.embosfer.fpinjava.chapter2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionsTest {

    @Test
    void exercise_2_7_partiallyApplyingFirstArgToACurriedFunctionOfTwoArgs() {
        Function<Integer, Function<Integer, Integer>> f = x -> y -> x + y;

        Function<Integer, Integer> g = Functions.partialA(5, f);
        assertEquals(10, g.apply(5));
    }

    @Test
    void exercise_2_8_partiallyApplyingSecondArgToACurriedFunctionOfTwoArgs() {
        Function<Integer, Function<Integer, Integer>> f = x -> y -> x / y;

        Function<Integer, Integer> divideByTwo = Functions.partialB(2, f);
        assertEquals(5, divideByTwo.apply(10));
    }

}