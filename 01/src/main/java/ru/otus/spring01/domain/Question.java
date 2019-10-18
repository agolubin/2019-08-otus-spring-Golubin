package ru.otus.spring01.domain;

public class Question {

    public String topic;
    public String[] possibleAnswer;
    public int rightAnswer;

    public Question(String topic, String[] possibleAnswer, int rightAnswer) {
        this.topic = topic;
        this.possibleAnswer = possibleAnswer;
        this.rightAnswer = rightAnswer;
    }

    public String getTopic() {
        return topic;
    }

    public String[] getPossibleAnswer() {
        return possibleAnswer;
    }

    public Integer getRightAnswer() {
        return rightAnswer;
    }
}
