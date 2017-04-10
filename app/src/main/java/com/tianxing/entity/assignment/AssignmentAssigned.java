package com.tianxing.entity.assignment;


import com.tianxing.entity.file.ImageFile;

import java.util.List;

/**
 * Created by tianxing on 2017/4/7.
 * 布置的作业
 */

public class AssignmentAssigned extends AbstractAssignment{


    private String title;//标题
    private String content;//内容
    private List<ImageFile> images;//图片信息


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ImageFile> getImages() {
        return images;
    }

    public void setImages(List<ImageFile> images) {
        this.images = images;
    }
}
