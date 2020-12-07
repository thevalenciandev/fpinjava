package com.thevalenciandev.fpinjava.chapter3;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static com.thevalenciandev.fpinjava.chapter3.CollectionUtilities.*;
import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilitiesTest {

    @Test
    void testEmptyList() {
        List<Double> list = list();
        assertEquals(Collections.emptyList(), list);
        assertIsImmutable(list, 1.0);
    }

    @Test
    void testListWithOneElement() {
        List<String> list = list("one");
        assertEquals(List.of("one"), list);
        assertIsImmutable(list, "two");
    }

   @Test
   void testListFromCollectionOfElements() {
       List<Integer> other = makeMutableListOf(1, 2, 3);
       List<Integer> list = list(other);
       assertEquals(other, list);
       assertIsImmutable(list, 4);
       assertNotSame(other, list);

       other.add(4); // Check that modifying our copy of the list doesn't affect our immutable list
       assertNotEquals(other, list);
   }

   @Test
   void testListOfVarArgs() {
       List<String> list = list("one", "two");
       assertEquals(List.of("one", "two"), list);
       assertIsImmutable(list, "two");
   }

    @Test
    void testListOfVarArgs_WithArray() {
        String[] elements = {"one", "two"};
        List<String> list = list(elements);
        assertEquals(List.of("one", "two"), list);
        assertIsImmutable(list, "two");

        elements[0] = "modified"; // Check that modifying the underlying array doesn't affect our immutable list
        assertEquals(List.of("one", "two"), list);
    }

    @Test
    void testHeadOfList() {
        List<Float> list = makeMutableListOf(1.0f, 2.0f, 3.0f);
        assertEquals(1.0f, head(list));
        assertEquals(List.of(1.0f, 2.0f, 3.0f), list);
    }

    @Test
    void testHeadOfList_EmptyList() {
        assertThrows(IllegalStateException.class, () -> head(Collections.emptyList()));
    }

    @Test
    void testTailOfList() {
        List<Number> other = makeMutableListOf(1, 2, 3);
        List<Number> tail = tail(other);
        assertIsImmutable(tail, 4);
        assertEquals(List.of(2, 3), tail);
        assertEquals(List.of(1, 2, 3), other); // Check original list has not been modified
        assertThrows(UnsupportedOperationException.class, () -> tail.remove(0));
    }

    @Test
    void testTailOfList_EmptyList() {
        assertThrows(IllegalStateException.class, () -> tail(Collections.emptyList()));
    }
    
    @Test
    void testAppendToList() {
        List<Integer> other = makeMutableListOf(1, 2);
        List<Integer> append = append(3, other);
        assertIsImmutable(append, 4);
        assertEquals(List.of(1, 2, 3), append);
        assertEquals(List.of(1, 2), other);
    }

    @Test
    void testPrependToList() {
        List<Integer> other = makeMutableListOf(1, 2);
        List<Integer> append = prepend(3, other);
        assertIsImmutable(append, 4);
        assertEquals(list(3, 1, 2), append);
        assertEquals(list(1, 2), other);
    }

    @Test
    void testFoldLeft() {
        List<Integer> list = list(1, 2, 3, 4, 5);
        assertEquals(15, foldLeft(list, 0, x -> y -> x + y));
        assertEquals(120, foldLeft(list, 1, x -> y -> x * y));
        assertEquals(120, foldLeftTailRec(list, 1, x -> y -> x * y));
        // Test different types as well
        Function<String, Function<Integer, String>> addStrings = s -> i -> "(" + s + " + " + i + ")";
        assertEquals("(((((0 + 1) + 2) + 3) + 4) + 5)", foldLeft(list, "0", addStrings));
    }
    
    @Test
    void testFoldRight() {
        List<Integer> list = list(1, 2, 3, 4, 5);
        assertEquals("(1 + (2 + (3 + (4 + (5 + 0)))))", foldRight(list, "0", x -> y -> addIS(x, y)));
        assertEquals("(1 + (2 + (3 + (4 + (5 + 0)))))", foldRightRecNaive(list, "0", x -> y -> addIS(x, y)));
        assertEquals("(1 + (2 + (3 + (4 + (5 + 0)))))", foldRightTailRec(list, "0", x -> y -> addIS(x, y)));
    }

    private static String addIS(Integer x, String y) {
        return "(" + x + " + " + y + ")";
    }

    @Test
    void testReverse() {
        assertEquals(list(5, 4, 3, 2, 1), reverse(list(1, 2, 3, 4, 5)));
    }

    @Test
    void testMap() {
        List<Integer> integers = List.of(1, 2);
        Function<Integer, Double> addTwentyPercent = x -> x * 1.2d;
        assertEquals(list(1.2, 2.4), map(integers, addTwentyPercent));
        assertEquals(list(1.2, 2.4), mapViaFoldLeft(integers, addTwentyPercent));
        assertEquals(list(1.2, 2.4), mapViaFoldRight(integers, addTwentyPercent));
    }

    @Test
    void testForEach() {
        List<Integer> list = list(1, 2, 3);
        AtomicInteger count = new AtomicInteger(0);
        forEach(list, i -> count.incrementAndGet());
        assertEquals(3, count.get());
    }

    @Test
    void testRange() {
        assertEquals(list(1, 2, 3), range(1, 4));
        assertEquals(list(1, 2, 3), rangeRec(1, 4));
        assertEquals(list(1, 2, 3), rangeTailRec(1, 4));
    }
    
    @Test
    void testUnfold() {
        assertEquals(list(1, 2, 3), unfold(1, x -> x + 1, x -> x < 4));
    }

    private <T> List<T> makeMutableListOf(T... elem) {
        return new ArrayList<>(Arrays.asList(elem));
    }

    private <T> void assertIsImmutable(List<? super T> list, T elementToAdd) {
        assertThrows(UnsupportedOperationException.class, () -> list.add(elementToAdd));
    }

}