package com.thevalenciandev.fpinjava.chapter2;

import com.thevalenciandev.fpinjava.chapter1.Tuple;

public final class Functions {

    public static <A, B, C> Function<B, C> partialA(A a, Function<A, Function<B, C>> f) {
        return f.apply(a);
    }

    public static <A, B, C> Function<A, C> partialB(B b, Function<A, Function<B, C>> f) {
        return a -> f.apply(a).apply(b);
    }

    public static <A, B, C> Function<A, Function<B, C>> curry(Function<Tuple<A, B>, C> f) {
        return a -> b -> f.apply(new Tuple<>(a, b));
    }
}
