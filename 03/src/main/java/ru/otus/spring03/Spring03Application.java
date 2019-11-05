package ru.otus.spring03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ru.otus.spring03.service.ExamService;



@SpringBootApplication
public class Spring03Application {

        public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Spring03Application.class, args);

        ExamService exam = context.getBean(ExamService.class);
        exam.testing();
        }
}
