package com.tianxing.model.communication.http.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by tianxing on 2017/4/1.
 * 自定义Json数据解析类
 */

public class JsonConverterFactory extends Converter.Factory {



    private JsonConverterFactory(ObjectMapper mapper){
        if (mapper == null) throw new NullPointerException();
        this.mapper = mapper;
    }

    private final ObjectMapper mapper;

    public static JsonConverterFactory create(){
        return new JsonConverterFactory(new ObjectMapper());
    }

    public static JsonConverterFactory create(ObjectMapper mapper){
        return new JsonConverterFactory(mapper);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return super.responseBodyConverter(type, annotations, retrofit);
    }
}
