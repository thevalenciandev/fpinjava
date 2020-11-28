package com.thevalenciandev.fpinjava.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * This is less useful on newer versions of Java containing the List.of API
 */
public class CollectionUtilities {

    public static <E> List<E> list() {
        return Collections.emptyList();
    }

    public static <E> List<E> list(E elem) {
        return Collections.singletonList(elem);
    }

    public static <E> List<E> list(List<E> other) {
        return Collections.unmodifiableList(new ArrayList<>(other));
    }

    public static <E> List<E> list(E... elements) {
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(elements, elements.length)));
    }

    public static <E> E head(List<E> elements) {
        if (elements.isEmpty()) {
            throw new IllegalStateException("Head of empty list");
        }
        return elements.get(0);
    }

    public static <E> List<E> tail(List<E> elements) {
        if (elements.isEmpty()) {
            throw new IllegalStateException("Tail of empty list");
        }
        List<E> copy = list(elements);
        return copy.subList(1, copy.size());
    }

    public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
        List<U> newList = new ArrayList<>();
        list.forEach(val -> newList.add(f.apply(val)));
        return newList;
    }

}
