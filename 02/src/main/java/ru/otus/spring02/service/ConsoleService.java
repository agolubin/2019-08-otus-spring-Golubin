package ru.otus.spring02.service;

import ru.otus.spring02.domain.Question;
import ru.otus.spring02.domain.Student;

public interface ConsoleService {

    Student askStudentInfo();
    boolean askQuestion(Question question, int number);
    public void printResult(int rightAnswer) ;
}
