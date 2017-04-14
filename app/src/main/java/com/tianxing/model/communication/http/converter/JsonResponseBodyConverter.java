package com.tianxing.model.communication.http.converter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by tianxing on 2017/4/1.
 * 解码网络请求的返回数据
 */

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    @Override
    public T convert(ResponseBody value) throws IOException {
        byte[] bytes = value.bytes();

        return null;
    }
}
