package space.efremov.otus.spring.controller;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import space.efremov.otus.spring.config.ApplicationConfig;

import java.util.Locale;

@ShellComponent
@ShellCommandGroup("locale")
public class LocaleController {

    private final ApplicationConfig config;

    public LocaleController(ApplicationConfig config) {
        this.config = config;
    }

    @ShellMethod(value = "Show current locale", key = {"locale-show", "locale show"})
    public String show() {
        return config.getLocale().getLanguage();
    }

    @ShellMethod(value = "Show current locale", key = {"locale-change", "locale change"})
    public void change(@ShellOption(help = "New locale")Locale locale) {
        config.setLocale(locale);
    }
}
