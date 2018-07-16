package space.efremov.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import space.efremov.otus.spring.domain.Question;
import space.efremov.otus.spring.domain.QuizResult;
import space.efremov.otus.spring.service.QuizService;

import java.util.List;

@Service
public class QuizServiceImp implements QuizService {
    @Override
    public QuizResult process(List<Question> questions) {
        return null;
    }
}
