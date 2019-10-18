package ru.otus.spring01.dao;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import ru.otus.spring01.domain.Question;

import java.io.FileReader;
import java.io.InputStreamReader;

public class QuestionDaoSimple implements QuestionDao {

    Question[] questions;

    public QuestionDaoSimple(String fileName) {
        CSVReader csvReader;
        try {
            String path = getClass().getClassLoader().getResource(fileName).toString();
            csvReader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)));
            char separator = ';';
            CSVParser parser = new CSVParserBuilder().withSeparator(separator)
                    .build();
            String[] nextLine;
            int index = 0;
            this.questions = new Question[5];
            while ((nextLine = csvReader.readNext()) != null) {
                for (String string : nextLine){
                    String[] value;
                    value = parser.parseLine(string);
                    String[] answers = new String[3];
                    answers[0] = value[1];
                    answers[1] = value[2];
                    answers[2] = value[3];

                    Question question = new Question(value[0], answers, Integer.valueOf(value[4]));
                    questions[index] = question;
                    index++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading file!!!");
        }
    }

    public Question findByNumber(Integer number) {
      return questions[number];
    }

    public Integer getCountQuestion(){
      return questions.length;
    }
}
