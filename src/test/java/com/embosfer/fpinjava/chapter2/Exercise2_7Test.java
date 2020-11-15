package com.embosfer.fpinjava.chapter2;

import org.junit.jupiter.api.Test;

import static com.embosfer.fpinjava.chapter2.Exercise2_7.partialA;
import static org.junit.jupiter.api.Assertions.*;

class Exercise2_7Test {

    @Test
    void canPartiallyApplyFirstArgumentToACurriedFunctionOfTwoArgs() {

        Function<Integer, Function<Integer, Integer>> f = x -> y -> x + y;

        Function<Integer, Integer> g = partialA(5, f);

        assertEquals(10, g.apply(5));
    }

}