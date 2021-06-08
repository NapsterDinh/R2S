package com.uniapp.r2scalendar.Model;

public class ModuleResponse {
    private int moduleId;
    private String adminId;
    private String moduleName;
    private String startTime;
    private String endTime;
    private int isDeleted;
    private String feedbackStartTime;
    private String feedbackEndTime;
    private int feedbackId;
    private String feedbackTitle;

    public ModuleResponse(String s, String toString, String string, String s1, String toString1, String string1, String s2) {
    }

    public ModuleResponse(int moduleId, String moduleName, String startTime, String endTime, int isDeleted, String adminId, int feedbackId, String feedbackStartTime, String feedbackEndTime ) {
        this.moduleId = moduleId;
        this.adminId = adminId;
        this.moduleName = moduleName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDeleted = isDeleted;
        this.feedbackStartTime = feedbackStartTime;
        this.feedbackEndTime = feedbackEndTime;
        this.feedbackId = feedbackId;
    }

    public ModuleResponse() {

    }

    public ModuleResponse(int moduleId, String moduleName, String adminId, String feedbackTitle, String startTime, String endTime, String feedbackStartTime, String feedbackEndTime, int i) {
        this.moduleId = moduleId;
        this.adminId = adminId;
        this.moduleName = moduleName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.feedbackStartTime = feedbackStartTime;
        this.feedbackEndTime = feedbackEndTime;
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public ModuleResponse(int moduleId, String moduleName, String startTime, String endTime, int isDeleted, String adminId, int feedbackId, String feedbackStartTime, String feedbackEndTime, String feedbackTitle) {
        this.moduleId = moduleId;
        this.adminId = adminId;
        this.moduleName = moduleName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDeleted = isDeleted;
        this.feedbackStartTime = feedbackStartTime;
        this.feedbackEndTime = feedbackEndTime;
        this.feedbackId = feedbackId;
        this.feedbackTitle = feedbackTitle;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int isDeleted() {
        return isDeleted;
    }

    public void setDeleted(int deleted) {
        isDeleted = deleted;
    }

    public String getFeedbackStartTime() {
        return feedbackStartTime;
    }

    public void setFeedbackStartTime(String feedbackStartTime) {
        this.feedbackStartTime = feedbackStartTime;
    }

    public String getFeedbackEndTime() {
        return feedbackEndTime;
    }

    public void setFeedbackEndTime(String feedbackEndTime) {
        this.feedbackEndTime = feedbackEndTime;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Override
    public String toString() {
        return moduleName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ModuleResponse){
            ModuleResponse c = (ModuleResponse )obj;
            if(c.getModuleName().equals(moduleName)) return true;
        }

        return false;
    }

}
