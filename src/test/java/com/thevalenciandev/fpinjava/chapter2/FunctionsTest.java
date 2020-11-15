package com.thevalenciandev.fpinjava.chapter2;

import com.thevalenciandev.fpinjava.chapter1.Tuple;
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

    @Test
    void exercise_2_10_curryingAFunctionOfTwoArgsModeledAsATuple() {
        Function<Tuple<Integer, Integer>, Integer> f = t -> t._1 + t._2;

        assertEquals(f.apply(new Tuple<>(6, 6)), Functions.curry(f).apply(6).apply(6));
    }

}