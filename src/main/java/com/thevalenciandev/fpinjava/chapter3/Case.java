package com.thevalenciandev.fpinjava.chapter3;

import com.thevalenciandev.fpinjava.chapter1.Tuple;

import java.util.function.Supplier;

public class Case<T> extends Tuple<Supplier<Boolean>, Supplier<Result<T>>> {

    public Case(Supplier<Boolean> condition, Supplier<Result<T>> value) {
        super(condition, value);
    }

    public static <T> Case<T> mcase(Supplier<Boolean> condition, Supplier<Result<T>> value) {
        return new Case<>(condition, value);
    }

    public static <T> DefaultCase<T> mcase(Supplier<Result<T>> value) {
        return new DefaultCase<>(value);
    }

    @SafeVarargs
    public static <T> Result<T> match(DefaultCase<T> defaultCase, Case<T>... matchers) {

        for (Case<T> matcher : matchers) {
            if (matcher._1.get()) {
                return matcher._2.get();
            }
        }

        return defaultCase._2.get();
    }

    private static class DefaultCase<T> extends Case<T> {
        private DefaultCase(Supplier<Result<T>> value) {
            super(() -> true, value);
        }
    }

}