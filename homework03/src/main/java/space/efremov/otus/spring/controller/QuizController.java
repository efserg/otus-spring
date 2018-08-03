package space.efremov.otus.spring.controller;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import space.efremov.otus.spring.service.QuizStarter;

@ShellComponent
@ShellCommandGroup("locale")
public class QuizController {

    private final QuizStarter starter;

    public QuizController(QuizStarter starter) {
        this.starter = starter;
    }

    @ShellMethod(value = "Run quiz", key = {"quiz run", "quiz-run"})
    public void run() {
        starter.start();
    }
}
