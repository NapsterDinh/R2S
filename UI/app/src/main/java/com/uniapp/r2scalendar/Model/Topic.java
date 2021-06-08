package com.uniapp.r2scalendar.Model;

public class Topic {
    private String TopicID;
    private String TopicName;

    public Topic(String topicID, String topicName) {
        TopicID = topicID;
        TopicName = topicName;
    }
    public Topic() {
    }
    public String getTopicID() {
        return TopicID;
    }

    public void setTopicID(String topicID) {
        TopicID = topicID;
    }

    public String getTopicName() {
        return TopicName;
    }

    public void setTopicName(String topicName) {
        TopicName = topicName;
    }
}
