package com.thevalenciandev.fpinjava.chapter2;

public class Exercise2_6 {

    static <T, U, V> Function<Function<T, U>,
                              Function<Function<U, V>,
                                       Function<T, V>>> higherAndThen() {
        return f -> g -> x -> g.apply(f.apply(x));
    }

    public static void main(String[] args) {
        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, Integer> square = x -> x * x;

        Function<Integer, Integer> tripleItThenSquareIt =
                Exercise2_6.<Integer, Integer, Integer>higherAndThen().apply(square).apply(triple);

        System.out.println(tripleItThenSquareIt.apply(2));
    }
}
