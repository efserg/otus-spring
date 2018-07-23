package space.efremov.otus.spring.service;

import org.springframework.stereotype.Service;
import space.efremov.otus.spring.domain.Question;
import space.efremov.otus.spring.domain.QuizResult;

import java.io.IOException;
import java.util.List;

@Service
public class QuizStarter {

    private final QuizReader quizReader;
    private final QuizResultProcessor quizResultProcessor;
    private final QuizService quizService;
    private final QuizUtils quizUtils;

    public QuizStarter(QuizReader quizReader, QuizResultProcessor quizResultProcessor, QuizService quizService, QuizUtils quizUtils) {
        this.quizReader = quizReader;
        this.quizResultProcessor = quizResultProcessor;
        this.quizService = quizService;
        this.quizUtils = quizUtils;
    }

    public void start() {
        try {
            final List<Question> questions = quizReader.readQuestions();
            final List<Question> quiz = quizUtils.getQuiz(questions);
            final QuizResult quizResult = quizService.process(quiz);
            quizResultProcessor.process(quizResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
