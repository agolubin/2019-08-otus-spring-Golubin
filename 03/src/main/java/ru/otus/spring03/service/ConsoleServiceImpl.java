package ru.otus.spring03.service;

import java.util.Locale;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring03.config.ApplicationSettings;
import ru.otus.spring03.domain.Question;
import ru.otus.spring03.domain.Student;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private final MessageSource messageSource;

    private final IOService ioService;

    @Autowired
    private ApplicationSettings settings;

    @Autowired
    public ConsoleServiceImpl(IOService ioService, MessageSource messageSource) {
        this.ioService = ioService;
        this.messageSource = messageSource;
    }

    public Student askStudentInfo() {
        ioService.printOut("Введите ФИО");
        /*ioService.printOut(
              messageSource.getMessage(
                      "exam.name",
                      null,
                      settings.locale
              ));

         */
      return new Student(ioService.readString());
    }

    public boolean askQuestion( Question question, int number){
        ioService.printOut("Вопрос ");
        /*
        ioService.printOut(
                messageSource.getMessage(
                        "exam.question",
                        new String [] {String.valueOf(number)},
                        settings.locale
                ));
*/
        ioService.printOut(question.topic);
      for (int j = 0; j < question.getPossibleAnswer().length; j++) {
          ioService.printOut(String.valueOf(j+1) + " - " + question.getPossibleAnswer()[j]);
      }

      int answer = ioService.readInt();
      return (answer == question.getRightAnswer());
   }

    public void printResult(int rightAnswer) {
        ioService.printOut("Результат ");
        /*
        ioService.printOut(
                messageSource.getMessage(
                        "exam.result",
                        null,
                        settings.locale
                ));

         */
        ioService.printOut(String.valueOf(rightAnswer));
    }

}
