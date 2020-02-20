package ru.otus.spring05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Console;

@SpringBootApplication
public class Spring05Application {

	public static void main(String[] args) throws Exception {
		Console.main(args);
		SpringApplication.run(Spring05Application.class, args);
	}

}
