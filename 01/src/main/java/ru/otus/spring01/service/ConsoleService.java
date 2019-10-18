package ru.otus.spring01.service;

import java.util.Scanner;
import ru.otus.spring01.domain.Question;

public interface ConsoleService {

    String askName(Scanner scanner);
    Boolean askQuestion(Scanner scanner, Question question, Integer number);
}
