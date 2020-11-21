package com.thevalenciandev.fpinjava.chapter3;

import java.util.function.Consumer;

public interface Result<T> {

    void bind(Consumer<T> success, Consumer<String> failure);

    static <T> Result<T> success(T t) {
        return new Success<>(t);
    }

    static <T> Result<T> failure(String message) {
        return new Failure<>(message);
    }

    class Success<T> implements Result<T> {

        private final T t;

        private Success(T t) {
            this.t = t;
        }

        @Override
        public void bind(Consumer<T> success, Consumer<String> failure) {
            success.accept(t);
        }
    }

    class Failure<T> implements Result<T> {

        private final String message;

        private Failure(String message) {
            this.message = message;
        }

        @Override
        public void bind(Consumer<T> success, Consumer<String> failure) {
            failure.accept(message);
        }
    }
}
