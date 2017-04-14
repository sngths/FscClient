package com.tianxing.pojo.assignment;

/**
 * Created by tianxing on 2017/4/7.
 * 作业批阅
 */

public class AssignmentFeedback extends AbstractAssignment{


    private int feedbackId;
    private int replyId;
    private String content;
    private int score;


    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }



    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
