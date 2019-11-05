package ru.otus.spring04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Spring04Application {


        public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Spring04Application.class, args);
        }
}
