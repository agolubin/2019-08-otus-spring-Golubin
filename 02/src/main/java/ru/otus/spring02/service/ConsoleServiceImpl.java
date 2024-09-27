package ru.otus.spring02.service;

import java.util.Locale;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring02.domain.Question;
import ru.otus.spring02.domain.Student;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private MessageSource messageSource;

    private IOService ioService;

    @Autowired
    public ConsoleServiceImpl(IOService ioService, MessageSource messageSource) {
        this.ioService = ioService;
        this.messageSource = messageSource;
    }


    @Value("${lang.locale}")
    private Locale locale;

    public Student askStudentInfo() {
        ioService.printOut(
              messageSource.getMessage(
                      "exam.name",
                      null,
                       locale
              ));
      return new Student(ioService.readString());
    }

    public boolean askQuestion( Question question, int number){
        ioService.printOut(
                messageSource.getMessage(
                        "exam.question",
                        new String [] {String.valueOf(number)},
                        locale
                ));

        ioService.printOut(question.topic);
      for (int j = 0; j < question.getPossibleAnswer().length; j++) {
          ioService.printOut(String.valueOf(j+1) + " - " + question.getPossibleAnswer()[j]);
      }

      int answer = ioService.readInt();
      return (answer == question.getRightAnswer());
   }

    public void printResult(int rightAnswer) {
        ioService.printOut(
                messageSource.getMessage(
                        "exam.result",
                        null,
                        locale
                ));
        ioService.printOut(String.valueOf(rightAnswer));
    }

}
