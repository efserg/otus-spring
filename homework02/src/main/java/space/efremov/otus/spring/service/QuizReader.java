package space.efremov.otus.spring.service;

import space.efremov.otus.spring.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuizReader {
    List<Question> getQuestions(String path) throws IOException;
}