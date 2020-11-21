package com.thevalenciandev.fpinjava.chapter3;

import java.util.regex.Pattern;

public class EmailValidation {

    final Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    void testMail(String email) {
        if (emailPattern.matcher(email).matches()) {
            sendVerificationEmail(email);
        } else {
            logError("email " + email + "is invalid");
        }
    }

    private void sendVerificationEmail(String email) {
        System.out.println("Verification mail sent to " + email);
    }

    private void logError(String email) {
        System.err.println("Error message logged:" + email);
    }
}