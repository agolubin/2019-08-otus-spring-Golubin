package ru.otus.spring03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import ru.otus.spring03.service.ExamService;

import java.util.Locale;

@SpringBootApplication
public class Spring03Application {

        public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Spring03Application.class, args);

        ExamService exam = context.getBean(ExamService.class);
        exam.testing();

        }
}
