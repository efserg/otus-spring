package space.efremov.otus.spring.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private final String text;
    private final Double weight;
    private final QuestionType type;
    private final List<Answer> answers;
}
