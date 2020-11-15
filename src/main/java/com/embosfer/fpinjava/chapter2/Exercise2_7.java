package com.embosfer.fpinjava.chapter2;

public class Exercise2_7 {

    static <A, B, C> Function<B, C> partialA(A a, Function<A, Function<B, C>> f) {
        return f.apply(a);
    }

}
