package com.thevalenciandev.fpinjava.chapter3;

import com.thevalenciandev.fpinjava.chapter2.Function;

import java.util.function.Consumer;
import java.util.regex.Pattern;

import static com.thevalenciandev.fpinjava.chapter3.Case.mcase;
import static com.thevalenciandev.fpinjava.chapter3.Result.failure;
import static com.thevalenciandev.fpinjava.chapter3.Result.success;

public final class EmailValidation2 {

    record Email(String address) {
    }

    static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
    static final Function<Email, Result<Email>> EMAIL_CHECKER = e -> Case.match(
            mcase(() -> success(e)),
            mcase(() -> e == null, () -> failure("email must not be null")),
            mcase(() -> e.address().length() == 0, () -> failure("email must not be empty")),
            mcase(() -> !EMAIL_PATTERN.matcher(e.address()).matches(), () -> failure("email " + e + " is invalid")));

    public static void main(String[] args) {
        Consumer<Email> success = e -> System.out.println(e.address());
        Consumer<String> failure = System.err::println;
        EMAIL_CHECKER.apply(new Email("this.is@my.email")).bind(success, failure);
        EMAIL_CHECKER.apply(null).bind(success, failure);
        EMAIL_CHECKER.apply(new Email("")).bind(success, failure);
        EMAIL_CHECKER.apply(new Email("john.doe@acme.com")).bind(success, failure);
    }
}