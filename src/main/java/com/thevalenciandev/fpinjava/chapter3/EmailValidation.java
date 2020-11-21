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
    static final Function<String, Result> EMAIL_CHECKER = e -> {
        if (e == null) {
            return new Result.Failure("email must not be null");
        } else if (e.length() == 0) {
            return new Result.Failure("email must not be empty");
        } else if (EMAIL_PATTERN.matcher(e).matches()) {
            return new Result.Success();
        } else {
            return new Result.Failure("email " + e + " is invalid");
        }
    };

    public static void validate(String email) {
        Result result = EMAIL_CHECKER.apply(email);
        if (result instanceof Result.Success) {
            sendVerificationEmail(email);
        } else {
            logError(((Result.Failure) result).getErrorMessage());
        }
    }

    private static void sendVerificationEmail(String email) {
        System.out.println("Verification mail sent to " + email);
    }

    private static void logError(String email) {
        System.err.println("Error message logged: " + email);
    }

    public static void main(String[] args) {
        validate("this.is@my.email");
        validate(null);
        validate("");
        validate("john.doe@acme.com");
    }
}
