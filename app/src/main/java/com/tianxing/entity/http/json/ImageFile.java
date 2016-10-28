package com.tianxing.entity.http.json;

/**
 * Created by tianxing on 16/9/23.
 */

public class ImageFile extends File{



    private String thumbnailUrl;

    private Integer height ;
    private Integer width;

    private Integer thumbnailHeight ;
    private Integer thumbnailWidth;

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getThumbnailHeight() {
        return thumbnailHeight;
    }

    public Integer getThumbnailWidth() {
        return thumbnailWidth;
    }
}
