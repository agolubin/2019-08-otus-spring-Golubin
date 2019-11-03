package ru.otus.spring02.dao;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.spring02.domain.Question;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

@Repository
public class QuestionDaoSimple implements QuestionDao {

    private ArrayList<Question> questions;

    public QuestionDaoSimple(@Value("${file.name}") String fileName, @Value("${lang.locale}")Locale locale) {
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName+"_"+locale.toString()+".csv")));
            char separator = ';';
            CSVParser parser = new CSVParserBuilder().withSeparator(separator)
                    .build();
            String[] nextLine;
            int index = 0;
            this.questions = new ArrayList<Question>();
            while ((nextLine = csvReader.readNext()) != null) {
                for (String string : nextLine){
                    String[] value;
                    value = parser.parseLine(string);
                    String[] answers = new String[3];
                    try{
                        answers[0] = value[1];
                        answers[1] = value[2];
                        answers[2] = value[3];
                        Question question = new Question(value[0], answers, Integer.valueOf(value[4]));
                        questions.add(question);
                        index++;
                    }
                     catch (Exception e) {
                            System.out.println("Ошибка загрузки вопрос №" + String.valueOf(index+1));
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Error loading file!!!");
        }
    }

    public Question findByNumber(int number) {
      return questions.get(number);
    }

    public int getCountQuestion(){
      return questions.size();
    }
}
