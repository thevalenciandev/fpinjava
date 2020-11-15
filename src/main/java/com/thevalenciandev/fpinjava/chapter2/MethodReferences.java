package com.thevalenciandev.fpinjava.chapter2;

public class MethodReferences {

    static <T, U, V> Function<T, V> compose(Function<T, U> f, Function<U, V> g) {
        return x -> g.apply(f.apply(x));
    }

    public static void main(String[] args) {
        System.out.println(MethodReferences.compose(
                (Double z) -> Math.PI / 2 - z,
                Math::sin)
                .apply(3.0));
    }
}
