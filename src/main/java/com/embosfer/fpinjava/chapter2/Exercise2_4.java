package com.embosfer.fpinjava.chapter2;

public class Exercise2_4 {

    public static void main(String[] args) {
        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, Integer> square = x -> x * x;

        Function<Function<Integer, Integer>,
                 Function<Function<Integer, Integer>,
                          Function<Integer, Integer>>> compose = f1 -> f2 -> x -> f1.apply(f2.apply(x));

        Function<Integer, Integer> tripleItThenSquareIt = compose.apply(square).apply(triple);

        System.out.println(tripleItThenSquareIt.apply(2));
    }
}
