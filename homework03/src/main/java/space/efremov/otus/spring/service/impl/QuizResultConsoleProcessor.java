package space.efremov.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import space.efremov.otus.spring.aspect.Loggable;
import space.efremov.otus.spring.config.ApplicationConfig;
import space.efremov.otus.spring.config.QuizConfig;
import space.efremov.otus.spring.domain.QuizResult;
import space.efremov.otus.spring.service.QuizResultProcessor;

import java.util.Locale;

@Service
public class QuizResultConsoleProcessor implements QuizResultProcessor {

    private final MessageSource ms;
    private final Locale locale;
    private final Double succeed;

    public QuizResultConsoleProcessor(MessageSource ms, QuizConfig quizConfig, ApplicationConfig appConfig) {
        this.ms = ms;
        this.locale = appConfig.getLocale();
        this.succeed = quizConfig.getQuestion().getSucceedPercentage();
    }


    @Override
    @Loggable
    public void process(QuizResult result) {
        System.out.println(ms.getMessage("quiz_result", new String[]{result.getStudent().toString()}, locale));
        System.out.println(result.getDateTime());
        final double percentage = result.getTotalScore() / result.getMaxScore() * 100;
        System.out.format("%.2f from %.2f (%.2f%%)\n", result.getTotalScore(), result.getMaxScore(), percentage);
        final String successOrFail = percentage >= succeed ?
                ms.getMessage("quiz_passed", new String[0], locale) :
                ms.getMessage("quiz_fail", new String[0], locale);
        System.out.println(successOrFail);

    }
}
