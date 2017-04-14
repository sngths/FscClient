package com.tianxing.pojo.http;


/**
 * Created by tianxing on 2017/4/12.
 *
 */
public class ResponseWrapper {
    private int statusCode;//http状态码
    private String resultMessage;
    private int type;//数据类型校验  为-1 说明结果为空


    private String rawData;//保存原始json数据 等待稍后解码成相应对象

}
