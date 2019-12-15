package ru.otus.spring06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Console;

@SpringBootApplication
public class Spring06Application {

        public static void main(String[] args) throws Exception {
                Console.main(args);
                SpringApplication.run(Spring06Application.class, args);
        }

}
