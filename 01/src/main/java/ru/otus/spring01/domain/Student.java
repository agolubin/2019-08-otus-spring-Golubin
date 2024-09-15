package ru.otus.spring01.domain;

public class Student {

    private  String name;
    private int rightAnswers;

    private Student() {
    }

    public Student(String name) {
        this.name = name;
    }

}
