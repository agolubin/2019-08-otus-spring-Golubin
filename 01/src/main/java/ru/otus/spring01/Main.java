package ru.otus.spring01;

import com.opencsv.CSVParserBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring01.domain.Question;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.CSVParser;
import ru.otus.spring01.service.ExamService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        ExamService exam = context.getBean(ExamService.class);
        exam.testing();

    }
}
