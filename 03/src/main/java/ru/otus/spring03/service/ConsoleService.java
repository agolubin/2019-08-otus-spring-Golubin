package ru.otus.spring03.service;

import ru.otus.spring03.domain.Question;
import ru.otus.spring03.domain.Student;

public interface ConsoleService {

    Student askStudentInfo();
    boolean askQuestion(Question question, int number);
    public void printResult(int rightAnswer) ;
}
