package com.embosfer.fpinjava.chapter2;

public class Exercise2_5 {

    static <T, U, V> Function<Function<U, V>,
                              Function<Function<T, U>,
                                       Function<T, V>>> higherCompose() {
        return f1 -> f2 -> x -> f1.apply(f2.apply(x));
    }

    public static void main(String[] args) {
        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, Integer> square = x -> x * x;

        Function<Integer, Integer> tripleItThenSquareIt =
                Exercise2_5.<Integer, Integer, Integer>higherCompose().apply(square).apply(triple);

        System.out.println(tripleItThenSquareIt.apply(2));
    }
}
