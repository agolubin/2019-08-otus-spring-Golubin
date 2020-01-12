package ru.otus.spring09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Console;

@SpringBootApplication
public class Spring09Application {

        public static void main(String[] args) throws Exception {
                Console.main(args);
                SpringApplication.run(Spring09Application.class, args);
        }

}
