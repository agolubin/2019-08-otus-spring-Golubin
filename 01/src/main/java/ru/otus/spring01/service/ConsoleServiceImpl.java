package ru.otus.spring01.service;

import java.util.Scanner;
import ru.otus.spring01.domain.Question;

public class ConsoleServiceImpl implements ConsoleService {



    public String askName(Scanner scanner) {
      System.out.print("Введите ФИО: ");
      return scanner.nextLine();
    }

    public Boolean askQuestion(Scanner scanner, Question question, Integer number){
      System.out.println("Вопрос №: " + String.valueOf(number));
      System.out.println(question.topic);
      for (int j = 0; j < question.getPossibleAnswer().length; j++) {
            System.out.println(String.valueOf(j+1) + " - " + question.getPossibleAnswer()[j]);
      }

      int answer = scanner.nextInt();
      if (answer == question.getRightAnswer()){
        return true;
      }
      else
        return false;
   }

}
