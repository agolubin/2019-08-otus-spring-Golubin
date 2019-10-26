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

    private Scanner scanner;

    public ConsoleServiceImpl(){
        this.scanner = new Scanner(System.in);
    }

    @Autowired
    private MessageSource messageSource;

    @Value("${lang.locale}")
    private Locale locale;

    public Student askStudentInfo() {
      System.out.println(
              messageSource.getMessage(
                      "exam.name",
                      null,
                       locale
              ));
      return new Student(scanner.nextLine());
    }

    public boolean askQuestion( Question question, int number){
        System.out.println(
                messageSource.getMessage(
                        "exam.question",
                        new String [] {String.valueOf(number)},
                        locale
                ));

      System.out.println(question.topic);
      for (int j = 0; j < question.getPossibleAnswer().length; j++) {
            System.out.println(String.valueOf(j+1) + " - " + question.getPossibleAnswer()[j]);
      }

      int answer = scanner.nextInt();
      return (answer == question.getRightAnswer());
   }

    public void printResult(int rightAnswer) {
        System.out.println(
                messageSource.getMessage(
                        "exam.result",
                        null,
                        locale
                ));
        System.out.println(String.valueOf(rightAnswer));
    }

}
