package space.efremov.otus.spring.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizResult {
    private final LocalDateTime dateTime;
    private final Student student;
    private final List<Question> questions;
    private final double totalScore;
    private final double maxScore;
}
