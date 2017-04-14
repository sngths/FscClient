package com.tianxing.fscteachersedition.http;

import com.tianxing.pojo.http.ResponseWrapper;

import org.junit.Test;

/**
 * Created by tianxing on 2017/4/13.
 *
 */

public class PojoTest {


    @Test
    public void hashValue(){
        System.out.println(ResponseWrapper.class.getName());
        System.out.println(ResponseWrapper.class.getName().hashCode());
    }
}
