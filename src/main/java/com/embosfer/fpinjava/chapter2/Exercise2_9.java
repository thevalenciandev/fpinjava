package com.embosfer.fpinjava.chapter2;

public class Exercise2_9 {

    static <A, B, C, D> String func(A a, B b, C c, D d) {
        return String.format("%s, %s, %s, %s", a, b, c, d);
    }

    static <A, B, C, D> Function<A, Function<B, Function<C, Function<D, String>>>> funcCurry() {
        return a -> b -> c -> d -> String.format("%s, %s, %s, %s", a, b, c, d);
    }
}
