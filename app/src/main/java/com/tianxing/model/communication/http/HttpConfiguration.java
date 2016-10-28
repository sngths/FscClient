package com.tianxing.model.communication.http;

/**
 * Created by tianxing on 16/9/1.
 */
public class HttpConfiguration {

    private static HttpConfiguration configuration;

    private String BaseUrl = "http://172.24.1.1:8080/";
    //private String BaseUrl = "http://localhost:8080/";


    private HttpConfiguration(){}

    public static HttpConfiguration getInstance(){
        if (configuration == null){
            configuration = new HttpConfiguration();
        }
        return configuration;
    }

    public String getBaseUrl() {
        return BaseUrl;
    }
}
