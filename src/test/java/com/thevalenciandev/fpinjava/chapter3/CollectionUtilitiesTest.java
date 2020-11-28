package com.thevalenciandev.fpinjava.chapter3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static com.thevalenciandev.fpinjava.chapter3.CollectionUtilities.map;
import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilitiesTest {

    @Test
    void testEmptyList() {
        List<Double> list = CollectionUtilities.list();
        assertEquals(Collections.emptyList(), list);
        assertThrows(UnsupportedOperationException.class, () -> list.add(1.0));
    }

    @Test
    void testListWithOneElement() {
        List<String> list = CollectionUtilities.list("one");
        assertEquals(List.of("one"), list);
        assertThrows(UnsupportedOperationException.class, () -> list.add("two"));
    }

   @Test
   void testListFromCollectionOfElements() {
       List<Integer> other = makeMutableListOf(1, 2, 3);
       List<Integer> list = CollectionUtilities.list(other);
       assertEquals(other, list);
       assertNotSame(other, list);

       other.add(4);
       assertNotEquals(other, list);
   }

   @Test
   void testListOfVarArgs() {
       List<String> list = CollectionUtilities.list("one", "two");
       assertEquals(List.of("one", "two"), list);
       assertThrows(UnsupportedOperationException.class, () -> list.add("two"));
   }

    @Test
    void testMap() {
        List<Integer> integers = List.of(1, 2);
        Function<Integer, Double> addTwentyPercent = x -> x * 1.2d;
        assertEquals(List.of(1.2, 2.4), map(integers, addTwentyPercent));
    }

    private <T> List<T> makeMutableListOf(T... elem) {
        return new ArrayList<>(Arrays.asList(elem));
    }
}