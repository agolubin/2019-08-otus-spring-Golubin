package ru.otus.spring06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Console;

@SpringBootApplication
public class spring06Application {

        public static void main(String[] args) throws Exception {
                Console.main(args);
                SpringApplication.run(spring06Application.class, args);
        }

}
