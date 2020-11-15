package com.embosfer.fpinjava.chapter2;

import org.junit.jupiter.api.Test;

import static com.embosfer.fpinjava.chapter2.Exercise2_9.*;
import static org.junit.jupiter.api.Assertions.*;

class Exercise2_9Test {

    @Test
    void traditionalJavaFunctionAndCurriedFunctionReturnTheSame() {

        String trad = func("1", "2", "3", "4");
        String curr = funcCurry().apply("1").apply("2").apply("3").apply("4");

        assertEquals(trad, curr);
    }

}