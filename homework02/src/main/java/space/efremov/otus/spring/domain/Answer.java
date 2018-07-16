package space.efremov.otus.spring.domain;

import lombok.Data;

@Data
public class Answer {
    private final Long id;
    private final Long questionId;
    private final String answer;
    private final Boolean isCorrect;
}
