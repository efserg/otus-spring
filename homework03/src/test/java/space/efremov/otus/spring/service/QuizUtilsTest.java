package space.efremov.otus.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import space.efremov.otus.spring.domain.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"
})
public class QuizUtilsTest {

    @Autowired
    private QuizUtils utils;

    @Test
    public void calculateResultTest() {
        Student student = new Student("Ivan", "Ivanov");
        final Answer q1A1 = new Answer("Q1A1", true);
        final Answer q1A2 = new Answer("Q1A2", false);
        final Question q1 = new Question("Q1", 1d, QuestionType.CHOICE, Arrays.asList(
                q1A1,
                q1A2,
                new Answer("Q1A3", false)
        ));
        final Answer q2A1 = new Answer("Q2A1", false);
        final Answer q2A2 = new Answer("Q2A2", true);
        final Question q2 = new Question("Q2", .1d, QuestionType.CHOICE, Arrays.asList(
                q2A1,
                q2A2,
                new Answer("Q2A3", false)
        ));
        final Answer q3A2 = new Answer("Q3A2", false);
        final Answer q3A3 = new Answer("Q3A3", true);
        final Question q3 = new Question("Q3", 1.1d, QuestionType.CHOICE, Arrays.asList(
                new Answer("Q3A1", false),
                q3A2,
                q3A3
        ));

        final HashMap<Question, Answer> fineResult = new HashMap<>(3);
        fineResult.put(q1, q1A1);
        fineResult.put(q2, q2A2);
        fineResult.put(q3, q3A3);

        final QuizResult calculateFineResult = utils.calculateResult(student, fineResult);
        assertEquals(calculateFineResult.getMaxScore(), 2.2, 0.0001);
        assertEquals(calculateFineResult.getTotalScore(), 2.2, 0.0001);

        final HashMap<Question, Answer> badResult = new HashMap<>(3);
        badResult.put(q1, q1A2);
        badResult.put(q2, q2A1);
        badResult.put(q3, q3A2);

        final QuizResult calculateBadResult = utils.calculateResult(student, badResult);
        assertEquals(calculateBadResult.getMaxScore(), 2.2, 0.0001);
        assertEquals(calculateBadResult.getTotalScore(), 0, 0.0001);

    }
    
}