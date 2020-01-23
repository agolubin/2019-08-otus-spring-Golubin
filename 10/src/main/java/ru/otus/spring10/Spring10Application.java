package ru.otus.spring10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Console;

@SpringBootApplication
public class Spring10Application {

        public static void main(String[] args) throws Exception {
//                Console.main(args);
                SpringApplication.run(Spring10Application.class, args);
        }

}
