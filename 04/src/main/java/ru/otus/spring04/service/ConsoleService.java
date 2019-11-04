package ru.otus.spring04.service;

import ru.otus.spring04.domain.Question;
import ru.otus.spring04.domain.Student;

public interface ConsoleService {

    Student askStudentInfo();
    boolean askQuestion(Question question, int number);
    public void printResult(int rightAnswer) ;
}
