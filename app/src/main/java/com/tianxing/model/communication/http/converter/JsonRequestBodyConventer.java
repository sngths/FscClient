package com.tianxing.model.communication.http.converter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by tianxing on 2017/4/1.
 * 编码网络请求的上传数据
 */

public class JsonRequestBodyConventer<T> implements Converter<T , RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        return null;
    }
}
