package space.efremov.otus.spring.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import space.efremov.otus.spring.aspect.Loggable;
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

    public QuizUtils(@Value("${question_count}") Integer count, @Value("${answer_shuffle}") Boolean isAnswerShuffle, @Value("${question_shuffle}") Boolean isQuestionShuffle) {
        this.count = count;
        this.isAnswerShuffle = isAnswerShuffle;
        this.isQuestionShuffle = isQuestionShuffle;
    }

    @Loggable
    public QuizResult calculateResult(Student student, Map<Question, Answer> answers) {
        final double totalScore = answers.entrySet().stream().map(pair -> pair.getValue().getIsCorrect() ? pair.getKey().getScore() : 0d).collect(Collectors.summarizingDouble(f -> f)).getSum();
        final double maxScore = answers.keySet().stream().map(Question::getScore).collect(Collectors.summarizingDouble(f -> f)).getSum();
        return new QuizResult(LocalDateTime.now(), student, new ArrayList<Question>(answers.keySet()), totalScore, maxScore);
    }

    @Loggable
    public List<Question> getQuiz(List<Question> questions) {
        final List<Question> questionsCopy = questions.stream().map(q -> {
            val answers = q.getAnswers().stream().map(a -> new Answer(a.getId(), a.getQuestionId(), a.getAnswer(), a.getIsCorrect())).collect(Collectors.toList());
            if (isAnswerShuffle) Collections.shuffle(answers);
            return new Question(q.getId(), q.getText(), q.getScore(), q.getQuestionType(), answers);
        }).collect(Collectors.toList());
        if (isQuestionShuffle) Collections.shuffle(questionsCopy);
        return questionsCopy.subList(0, count);
    }

}
