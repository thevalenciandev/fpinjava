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
        List<E> copy = copy(elements);
        copy.remove(0);
        return Collections.unmodifiableList(copy);
    }

    private static <E> List<E> copy(List<E> list) {
        return new ArrayList<>(list);
    }

    public static <E> List<E> append(List<E> list, E element) {
        List<E> copy = copy(list);
        copy.add(element);
        return Collections.unmodifiableList(copy);
    }

    public static <T, U> U foldLeft(List<T> list, U identity, Function<U, Function<T, U>> f) {
        U res = identity;
        for (T elem : list) {
            res = f.apply(res).apply(elem);
        }
        return res;
    }

    public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
        List<U> newList = new ArrayList<>();
        list.forEach(val -> newList.add(f.apply(val)));
        return newList;
    }

}
