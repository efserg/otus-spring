package space.efremov.otus.spring.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private final Long id;
    private final String text;
    private final Double score;
    private final QuestionType questionType;
    private final List<Answer> answers;
}
