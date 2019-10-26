package ru.otus.spring02.domain;

import org.springframework.stereotype.Component;

@Component
public class Student {

    private  String name;
    private int rightAnswers;

    private Student() {
    }

    public Student(String name) {
        this.name = name;
    }

}
