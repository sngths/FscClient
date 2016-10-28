package com.tianxing.entity.http.json;

/**
 * Created by tianxing on 16/10/27.
 * 上传文件到服务器的返回文件信息类
 */

public class File {

    private String name ;
    private String originalFilename;
    private String url ;
    private String date;
    private Long fileSize;

    public String getName() {
        return name;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public Long getFileSize() {
        return fileSize;
    }
}
