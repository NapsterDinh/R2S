package com.uniapp.r2scalendar.Model;

public class Feedback {
    public Feedback() {
    }
    private String feedbackId;
    private String title;
    private String adminId;
    private Integer isDeleted;

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getTypeFeedbackID() {
        return typeFeedbackID;
    }

    public void setTypeFeedbackID(Integer typeFeedbackID) {
        this.typeFeedbackID = typeFeedbackID;
    }

    private Integer typeFeedbackID;

}

