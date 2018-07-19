package space.efremov.otus.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import space.efremov.otus.spring.domain.Question;
import space.efremov.otus.spring.domain.QuizResult;
import space.efremov.otus.spring.service.QuizReader;
import space.efremov.otus.spring.service.QuizResultProcessor;
import space.efremov.otus.spring.service.QuizService;
import space.efremov.otus.spring.service.QuizUtils;

import java.util.List;

@ComponentScan
@Configuration
@PropertySource("classpath:application.yml")
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class)) {
            final QuizReader quizReader = context.getBean(QuizReader.class);
            final QuizService quizService = context.getBean(QuizService.class);
            final QuizUtils quizUtils = context.getBean(QuizUtils.class);
            final QuizResultProcessor quizResultProcessor = context.getBean(QuizResultProcessor.class);
            final List<Question> questions = quizReader.readQuestions();
            final List<Question> quiz = quizUtils.getQuiz(questions);
            final QuizResult quizResult = quizService.process(quiz);
            quizResultProcessor.process(quizResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/messages");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

}
