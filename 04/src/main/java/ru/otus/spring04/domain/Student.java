package ru.otus.spring04.domain;

import org.springframework.stereotype.Component;

@Component
public class Student {

    private  String name;

    private Student() {
    }

    public Student(String name) {
        this.name = name;
    }

}
