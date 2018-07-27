package space.efremov.otus.spring.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import space.efremov.otus.spring.aspect.Loggable;
import space.efremov.otus.spring.config.ApplicationConfig;
import space.efremov.otus.spring.config.QuizConfig;
import space.efremov.otus.spring.domain.Question;
import space.efremov.otus.spring.service.QuizReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class QuizYamlReader implements QuizReader {


    private final ApplicationConfig appConfig;

    private final QuizConfig quizConfig;

    @Autowired
    public QuizYamlReader(ApplicationConfig appConfig, QuizConfig quizConfig) {
        this.appConfig = appConfig;
        this.quizConfig = quizConfig;
    }

    @Override
    @Loggable
    public List<Question> readQuestions() throws IOException {
        final String quizFilename = quizConfig.getQuizFilename(appConfig.getLocale());
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        final CollectionLikeType collectionLikeType = mapper.getTypeFactory().constructCollectionLikeType(List.class, Question.class);
        return mapper.readValue(ResourceUtils.getFile(quizFilename), collectionLikeType);
    }

}
