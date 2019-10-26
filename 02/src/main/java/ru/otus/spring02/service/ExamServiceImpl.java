package ru.otus.spring02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring02.domain.Student;

@Service
public class ExamServiceImpl implements ExamService {

    private Student student;
    private QuestionService questionService;
    private ConsoleService console;
    public void  setStudent (Student student){
        this.student = student;
    };

    public void  setQuestionService (QuestionService questionService){
        this.questionService = questionService;
    };

    public void  setConsoleService (ConsoleService сonsoleService){
        this.console = сonsoleService;
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
      this.student = console.askName();
      int res = 0;
      for (int i = 0; i < questionService.getCountQuestion(); i++){
        if (console.askQuestion( questionService.getByNumber(i), i+1)) {
            res++;
        }
      }
    console.printResult(res);
    }
}
