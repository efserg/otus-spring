package space.efremov.otus.spring.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private final Long id;
    private final List<String> answers;
    private final Double score;
    private final QuestionType questionType;
}
