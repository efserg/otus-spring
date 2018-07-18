package space.efremov.otus.spring.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import space.efremov.otus.spring.domain.Answer;
import space.efremov.otus.spring.domain.Question;
import space.efremov.otus.spring.domain.QuestionType;
import space.efremov.otus.spring.service.QuizReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuizCsvReader implements QuizReader {

    private final String questionPath;

    private final String answerPath;

    public QuizCsvReader(@Value("${questions}") String questionPath, @Value("${answers}") String answerPath) {
        this.questionPath = questionPath;
        this.answerPath = answerPath;
    }

    @Override
    public List<Question> readQuestions() throws IOException {
        final Map<Long, List<Answer>> answersByQuestionId = CSVParser.parse(ResourceUtils.getFile(answerPath), Charset.forName("UTF-8"), CSVFormat.DEFAULT).getRecords().stream().map(this::csvToAnswer).collect(Collectors.groupingBy(Answer::getQuestionId));

        return CSVParser.parse(ResourceUtils.getFile(questionPath), Charset.forName("UTF-8"), CSVFormat.DEFAULT).getRecords().stream().map(csv -> csvToQuestion(answersByQuestionId, csv)).collect(Collectors.toList());
    }

    private Question csvToQuestion(Map<Long, List<Answer>> answersByQuestionId, CSVRecord csv) {
        Long id = Long.parseLong(csv.get(0));
        return new Question(
                id,
                csv.get(1),
                Double.parseDouble(csv.get(2)),
                QuestionType.valueOf(csv.get(3).trim().toUpperCase()),
                answersByQuestionId.getOrDefault(id, Collections.emptyList())
        );
    }

    private Answer csvToAnswer(CSVRecord csv) {
        Long id = Long.parseLong(csv.get(0));
        return new Answer(
                id,
                Long.parseLong(csv.get(1)),
                csv.get(2),
                Boolean.parseBoolean(csv.get(3)));
    }
}
