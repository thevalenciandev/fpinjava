package com.thevalenciandev.fpinjava.chapter3;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static com.thevalenciandev.fpinjava.chapter3.CollectionUtilities.map;
import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilitiesTest {

    @Test
    void testMap() {
        List<Integer> integers = List.of(1, 2);
        Function<Integer, Double> addTwentyPercent = x -> x * 1.2d;
        assertEquals(List.of(1.2, 2.4), map(integers, addTwentyPercent));
    }

}