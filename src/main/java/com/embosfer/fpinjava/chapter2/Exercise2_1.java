package com.embosfer.fpinjava.chapter2;

public class Exercise2_1 {

    public static void main(String[] args) {
        Function<Integer, Integer> triple = arg -> arg * 3;
        Function<Integer, Integer> square = arg -> arg * arg;

        Function<Integer, Integer> squareItThenTripleIt = compose(triple, square);
        System.out.println(squareItThenTripleIt.apply(3));
    }


    static Function<Integer, Integer> compose(Function<Integer, Integer> f1, Function<Integer, Integer> f2) {
        return arg -> f1.apply(f2.apply(arg));
    }
}
