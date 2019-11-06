package ru.otus.spring04.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.spring04.dao.QuestionDaoSimple;
import ru.otus.spring04.config.ApplicationSettings;

import java.util.Locale;

@Configuration
public class ApplicationConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms
                = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    QuestionDaoSimple questionDaoSimple(ApplicationSettings settings) {
        return new QuestionDaoSimple(settings.fileName, settings.locale);
    }

}
