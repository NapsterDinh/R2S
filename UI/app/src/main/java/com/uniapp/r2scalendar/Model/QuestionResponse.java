package com.uniapp.r2scalendar.Model;

public class QuestionResponse {
    public QuestionResponse() {

    }

    public int getTopicID() {
        return TopicID;
    }

    public void setTopicID(int topicID) {
        TopicID = topicID;
    }

    private int TopicID;
    private String TopicName;
    private int QuestionID;
    private String QuestionContent;

    public QuestionResponse(int topicID,String questionContent, String topicName,int questionID) {
        TopicID = topicID;
        TopicName = topicName;
        QuestionContent = questionContent;
        QuestionID=questionID;

    }

    public String getTopicName() {
        return TopicName;
    }

    public void setTopicName(String topicName) {
        TopicName = topicName;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public String getQuestionContent() {
        return QuestionContent;
    }

    public void setQuestionContent(String questionContent) {
        QuestionContent = questionContent;
    }
}
