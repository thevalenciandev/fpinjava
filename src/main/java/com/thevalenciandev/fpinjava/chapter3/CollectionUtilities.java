package com.thevalenciandev.fpinjava.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CollectionUtilities {

    static <T, U> List<U> map(List<T> list, Function<T, U> f) {
        List<U> newList = new ArrayList<>();
        list.forEach(val -> newList.add(f.apply(val)));
        return newList;
    }

}
