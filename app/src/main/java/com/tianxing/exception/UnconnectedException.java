package com.tianxing.exception;

/**
 * Created by tianxing on 16/8/20.
 */
public class UnconnectedException extends RuntimeException {

    public UnconnectedException(){}

    public UnconnectedException(String message){
        super(message);
    }

}
