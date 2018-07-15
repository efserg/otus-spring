package space.efremov.otus.spring.service;

import space.efremov.otus.spring.domain.Question;
import space.efremov.otus.spring.domain.QuizResult;

import java.util.List;

public interface QuizService {
    QuizResult process(List<Question> questions);
}
