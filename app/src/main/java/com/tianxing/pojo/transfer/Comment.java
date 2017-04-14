package com.tianxing.pojo.transfer;

/**
 * Created by tianxing on 16/11/4.
 * 老师用户上传的一条批阅信息
 */

public class Comment {

    private String replyID;
    private int score;//分数
    private String content;//评语

    public String getReplyID() {
        return replyID;
    }

    public void setReplyID(String replyID) {
        this.replyID = replyID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
