package com.thevalenciandev.fpinjava.chapter2;

public class Exercise2_6 {

    public static void main(String[] args) {
        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, Integer> square = x -> x * x;

        Function<Integer, Integer> tripleItThenSquareIt =
                Function.<Integer, Integer, Integer>higherAndThen().apply(square).apply(triple);

        System.out.println(tripleItThenSquareIt.apply(2));
    }
}
