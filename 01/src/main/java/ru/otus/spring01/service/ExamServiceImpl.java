package ru.otus.spring01.service;

import ru.otus.spring01.domain.Student;
import ru.otus.spring01.domain.Question;

import java.util.Scanner;

public class ExamServiceImpl implements ExamService {

    public Student student;
    public QuestionService questionService;
    public ConsoleService console;
    public void  setStudent (Student student){
        this.student = student;
    };

    public void  setQuestionService (QuestionService questionService){
        this.questionService = questionService;
    };

    public void  setConsoleService (ConsoleService сonsoleService){
        this.console = сonsoleService;
    };

    public ExamServiceImpl(QuestionService questionService, ConsoleService console, Student student) {
        this.student = student;
        this.questionService = questionService;
        this.console = console;
    }

    public ExamServiceImpl() {
    }

    public Integer testing() {
      Scanner scanner = new Scanner(System.in);
      student.name = console.askName(scanner);
      Integer res = 0;
      for (int i = 0; i < questionService.getCountQuestion(); i++){
        if (console.askQuestion(scanner, questionService.getByNumber(i), i+1)) {
            res++;
        }
      }
    System.out.println("Количество правильных ответов");
    System.out.println(String.valueOf(res));
    return res;


    }
}
