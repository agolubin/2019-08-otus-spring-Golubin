package ru.otus.spring01.service;

import ru.otus.spring01.domain.Question;
import ru.otus.spring01.domain.Student;

public interface ConsoleService {

    Student askName();
    boolean askQuestion(Question question, int number);
    public void printResult(int rightAnswer) ;
}
