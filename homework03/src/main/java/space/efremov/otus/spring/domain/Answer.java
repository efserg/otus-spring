package space.efremov.otus.spring.domain;

import lombok.Data;

@Data
public class Answer {
    private final String text;
    private final Boolean isCorrect;
}
