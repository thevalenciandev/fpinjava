package com.thevalenciandev.fpinjava.chapter3;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;

import static com.thevalenciandev.fpinjava.chapter3.CollectionUtilities.map;
import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilitiesTest {

    @Test
    void testEmptyList() {
        List<Double> list = CollectionUtilities.list();
        assertEquals(Collections.emptyList(), list);
        assertIsImmutable(list, 1.0);
    }

    @Test
    void testListWithOneElement() {
        List<String> list = CollectionUtilities.list("one");
        assertEquals(List.of("one"), list);
        assertIsImmutable(list, "two");
    }

   @Test
   void testListFromCollectionOfElements() {
       List<Integer> other = makeMutableListOf(1, 2, 3);
       List<Integer> list = CollectionUtilities.list(other);
       assertEquals(other, list);
       assertIsImmutable(list, 4);
       assertNotSame(other, list);

       other.add(4); // Check that modifying our copy of the list doesn't affect our immutable list
       assertNotEquals(other, list);
   }

   @Test
   void testListOfVarArgs() {
       List<String> list = CollectionUtilities.list("one", "two");
       assertEquals(List.of("one", "two"), list);
       assertIsImmutable(list, "two");
   }

    @Test
    void testListOfVarArgs_WithArray() {
        String[] elements = {"one", "two"};
        List<String> list = CollectionUtilities.list(elements);
        assertEquals(List.of("one", "two"), list);
        assertIsImmutable(list, "two");

        elements[0] = "modified"; // Check that modifying the underlying array doesn't affect our immutable list
        assertEquals(List.of("one", "two"), list);
    }

    @Test
    void testHeadOfList() {
        List<Float> list = makeMutableListOf(1.0f, 2.0f, 3.0f);
        assertEquals(1.0f, CollectionUtilities.head(list));
        assertEquals(List.of(1.0f, 2.0f, 3.0f), list);
    }

    @Test
    void testHeadOfList_EmptyList() {
        assertThrows(IllegalStateException.class, () -> CollectionUtilities.head(Collections.emptyList()));
    }

    @Test
    void testTailOfList() {
        List<Number> other = makeMutableListOf(1, 2, 3);
        List<Number> tail = CollectionUtilities.tail(other);
        assertIsImmutable(tail, 4);
        assertEquals(List.of(2, 3), tail);
        assertEquals(List.of(1, 2, 3), other); // Check original list has not been modified
        assertThrows(UnsupportedOperationException.class, () -> tail.remove(0));
    }

    @Test
    void testTailOfList_EmptyList() {
        assertThrows(IllegalStateException.class, () -> CollectionUtilities.tail(Collections.emptyList()));
    }
    
    @Test
    void testAppendToList() {
        List<Integer> other = makeMutableListOf(1, 2);
        List<Integer> append = CollectionUtilities.append(other, 3);
        assertIsImmutable(append, 4);
        assertEquals(List.of(1, 2, 3), append);
        assertEquals(List.of(1, 2), other);
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

    private <T> void assertIsImmutable(List<? super T> list, T elementToAdd) {
        assertThrows(UnsupportedOperationException.class, () -> list.add(elementToAdd));
    }

}