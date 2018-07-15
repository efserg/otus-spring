package space.efremov.otus.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import space.efremov.otus.spring.domain.Question;
import space.efremov.otus.spring.domain.QuizResult;
import space.efremov.otus.spring.service.QuizReader;
import space.efremov.otus.spring.service.QuizService;

import java.io.IOException;
import java.util.List;

@ComponentScan
@Configuration
@PropertySource("classpath:application.yml")
public class Application {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class)) {
            QuizReader quizReader = context.getBean(QuizReader.class);
            QuizService quizService = context.getBean(QuizService.class);
            List<Question> quiz = quizReader.getQuestions("");
            QuizResult quizResult = quizService.process(quiz);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

}
