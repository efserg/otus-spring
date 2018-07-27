package space.efremov.otus.spring.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import space.efremov.otus.spring.aspect.Loggable;
import space.efremov.otus.spring.config.ApplicationConfig;
import space.efremov.otus.spring.config.QuizConfig;
import space.efremov.otus.spring.domain.Answer;
import space.efremov.otus.spring.domain.Question;
import space.efremov.otus.spring.domain.QuizResult;
import space.efremov.otus.spring.domain.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuizUtils {

    private final Integer count;
    private final Boolean isAnswerShuffle;
    private final Boolean isQuestionShuffle;

    @Autowired
    public QuizUtils(QuizConfig quizConfig) {
        this.count = quizConfig.getQuestion().getCount();
        this.isAnswerShuffle = quizConfig.getAnswer().isShuffle();
        this.isQuestionShuffle = quizConfig.getQuestion().isShuffle();
    }

    @Loggable
    public QuizResult calculateResult(Student student, Map<Question, Answer> answers) {
        final double totalScore = answers.entrySet().stream().map(pair -> pair.getValue().getIsCorrect() ? pair.getKey().getWeight() : 0d).collect(Collectors.summarizingDouble(f -> f)).getSum();
        final double maxScore = answers.keySet().stream().map(Question::getWeight).collect(Collectors.summarizingDouble(f -> f)).getSum();
        return new QuizResult(LocalDateTime.now(), student, new ArrayList<Question>(answers.keySet()), totalScore, maxScore);
    }

    @Loggable
    public List<Question> getQuiz(List<Question> questions) {
        final List<Question> questionsCopy = questions.stream().map(q -> {
            val answers = q.getAnswers().stream().map(a -> new Answer(a.getText(), a.getIsCorrect())).collect(Collectors.toList());
            if (isAnswerShuffle) Collections.shuffle(answers);
            return new Question(q.getText(), q.getWeight(), q.getType(), answers);
        }).collect(Collectors.toList());
        if (isQuestionShuffle) Collections.shuffle(questionsCopy);
        return questionsCopy.subList(0, count);
    }

}
