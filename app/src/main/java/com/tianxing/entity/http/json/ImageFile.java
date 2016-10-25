package com.tianxing.entity.http.json;

/**
 * Created by tianxing on 16/9/23.
 */

public class ImageFile {

    private String name = "";
    private String url = "";

    private String date;
    private String thumbnailUrl = "";

    private Integer height ;
    private Integer width;

    private Integer thumbnailHeight ;
    private Integer thumbnailWidth;

    private Long fileSize;


    public Integer getHeight() {
        return height;
    }

    public Integer getThumbnailHeight() {
        return thumbnailHeight;
    }

    public Integer getThumbnailWidth() {
        return thumbnailWidth;
    }

    public Integer getWidth() {
        return width;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }
}
