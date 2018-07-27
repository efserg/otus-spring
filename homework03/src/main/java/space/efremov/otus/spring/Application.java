package space.efremov.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import space.efremov.otus.spring.domain.Question;
import space.efremov.otus.spring.domain.QuizResult;
import space.efremov.otus.spring.service.*;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        final QuizStarter quizStarter = context.getBean(QuizStarter.class);
        quizStarter.start();
    }

    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/messages");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
