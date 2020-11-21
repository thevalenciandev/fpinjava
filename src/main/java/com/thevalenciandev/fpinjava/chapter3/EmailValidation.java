package com.thevalenciandev.fpinjava.chapter3;

import com.thevalenciandev.fpinjava.chapter2.Function;

import java.util.regex.Pattern;

public class EmailValidation {

    interface Result {
        class Success implements Result {
        }
        class Failure implements Result {
            private final String errorMessage;

            Failure(String errorMessage) {
                this.errorMessage = errorMessage;
            }

            String getErrorMessage() {
                return errorMessage;
            }
        }
    }

    static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
    static final Function<String, Result> EMAIL_CHECKER = e ->
        e == null
            ? new Result.Failure("email must not be null")
            : e.length() == 0
                ? new Result.Failure("email must not be empty")
                : EMAIL_PATTERN.matcher(e).matches()
                    ? new Result.Success()
                    : new Result.Failure("email " + e + " is invalid");

    public static Executable validate(String email) {
        Result result = EMAIL_CHECKER.apply(email);
        return (result instanceof Result.Success)
                ? () -> sendVerificationEmail(email)
                : () -> logError(((Result.Failure) result).getErrorMessage());
    }

    private static void sendVerificationEmail(String email) {
        System.out.println("Verification mail sent to " + email);
    }

    private static void logError(String email) {
        System.err.println("Error message logged: " + email);
    }

    public static void main(String[] args) {
        validate("this.is@my.email").exec();
        validate(null).exec();
        validate("").exec();
        validate("john.doe@acme.com").exec();
    }
}
