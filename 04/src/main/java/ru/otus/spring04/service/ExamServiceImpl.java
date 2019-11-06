package ru.otus.spring04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring04.domain.Student;

@Service
public class ExamServiceImpl implements ExamService {

    private Student student;
    private QuestionService questionService;
    private ConsoleService console;
    private int res;
    public void  setStudent (Student student){
        this.student = student;
    };

    public void  setQuestionService (QuestionService questionService){
        this.questionService = questionService;
    };

    public void  setConsoleService (ConsoleService consoleService){
        this.console = consoleService;
    };

    @Autowired
    public ExamServiceImpl(QuestionService questionService, ConsoleService console, Student student) {
        this.student = student;
        this.questionService = questionService;
        this.console = console;
    }

    public ExamServiceImpl() {
    }

    public void testing() {
      this.student = console.askStudentInfo();
      for (int i = 0; i < questionService.getCountQuestion(); i++){
        if (console.askQuestion( questionService.getByNumber(i), i+1)) {
            this.res++;
        }
      }
    console.printResult(res);
    }

    public void askStudentInfo() {
        this.student = console.askStudentInfo();
    }

    public void askQuestion() {
        for (int i = 0; i < questionService.getCountQuestion(); i++){
            if (console.askQuestion( questionService.getByNumber(i), i+1)) {
                this.res++;
            }
        }
    }

    public void printResult() {
       console.printResult(this.res);
    }

}
