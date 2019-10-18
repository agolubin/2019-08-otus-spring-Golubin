package ru.otus.spring01.service;

import ru.otus.spring01.dao.QuestionDao;
import ru.otus.spring01.domain.Question;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao2) {
        this.dao = dao2;
    }
    public Question getByNumber(Integer number) {
        return dao.findByNumber(number);
    }
    public Integer getCountQuestion() {
        return dao.getCountQuestion();
    }
}
