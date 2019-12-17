package ru.otus.spring07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Console;

@SpringBootApplication
public class Spring07Application {

        public static void main(String[] args) throws Exception {
                Console.main(args);
                SpringApplication.run(Spring07Application.class, args);
        }

}
