package space.efremov.otus.spring.service.impl;

import space.efremov.otus.spring.domain.Question;
import space.efremov.otus.spring.service.QuizReader;

import java.io.IOException;
import java.util.List;

public class QuizCsvReader implements QuizReader {
    @Override
    public List<Question> getQuestions(String path) throws IOException {
        return null;
    }
}
