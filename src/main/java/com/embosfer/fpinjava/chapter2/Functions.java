package com.embosfer.fpinjava.chapter2;

public final class Functions {

    public static <A, B, C> Function<A, C> partialB(B b, Function<A, Function<B, C>> f) {
        return a -> f.apply(a).apply(b);
    }
}
