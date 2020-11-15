package com.embosfer.fpinjava.chapter2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsTest {

    @Test
    void exercise_2_8_partiallyApplyingSecondArgToACurriedFunctionOfTwoArgs() {
        Function<Integer, Function<Integer, Integer>> f = x -> y -> x / y;

        Function<Integer, Integer> divideByTwo = Functions.partialB(2, f);
        assertEquals(5, divideByTwo.apply(10));
    }

}