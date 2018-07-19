package space.efremov.otus.spring.service.impl;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import space.efremov.otus.spring.aspect.Loggable;
import space.efremov.otus.spring.domain.Answer;
import space.efremov.otus.spring.domain.Question;
import space.efremov.otus.spring.domain.QuizResult;
import space.efremov.otus.spring.domain.Student;
import space.efremov.otus.spring.service.QuizService;
import space.efremov.otus.spring.service.QuizUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ConsoleQuizService implements QuizService {

    private final QuizUtils quizUtils;
    private final MessageSource ms;
    private final Locale locale;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ConsoleQuizService(MessageSource ms, QuizUtils quizUtils, @Value("${locale}") Locale locale) {
        this.ms = ms;
        this.quizUtils = quizUtils;
        this.locale = locale;
    }

    @Override
    @Loggable
    public QuizResult process(List<Question> questions) throws IOException {
        sayHello();
        val student = createUser();
        return startQuiz(student, questions);
    }

    private QuizResult startQuiz(Student student, List<Question> quiz) {

        final HashMap<Question, Answer> answers = new HashMap<>();

        for (int i = 0; i < quiz.size(); i++) {
            val q = quiz.get(i);
            System.out.println(ms.getMessage("question_header", new String[]{String.valueOf(i + 1)}, locale));
            System.out.println(q.getText());
            val j = new AtomicInteger(0);
            q.getAnswers().forEach(a -> System.out.format("%d) %s\n", j.incrementAndGet(), a.getAnswer()));
            System.out.println(ms.getMessage("answer_choice", new String[0], locale));
            String input;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            final int index = Integer.valueOf(input) - 1;
            answers.put(q, q.getAnswers().get(index));
        }
        return quizUtils.calculateResult(student, answers);
    }

    private void sayHello() {
        System.out.println(ms.getMessage("hello", new String[0], locale));
    }

    private Student createUser() throws IOException {
        System.out.print(ms.getMessage("enter_name", new String[0], locale));
        val input = reader.readLine().split("\\s");
        return new Student(input[0], input.length > 1 ? input[1] : "");
    }


}
