package com.tianxing.fscteachersedition;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void UUID(){
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
        System.out.println("asdasd.jpg".split("\\.")[1]);
        String name = "name";
        System.out.println(name.concat("aa"));
    }
}