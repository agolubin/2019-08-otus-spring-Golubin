package ru.otus.spring04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;


@SpringBootApplication
public class Spring04Application {


        public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Spring04Application.class, args);
        }
}
