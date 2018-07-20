package space.efremov.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@ConfigurationProperties("quiz.config")
public class QuizConfig {

    private Answer answer;
    private Question question;
    private String path;
    private String filename;

    public String getQuizFilename(Locale locale) {
        return String.format("%s/%s/%s", path, locale.getDisplayLanguage().toLowerCase(), filename);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public static class Answer {
        private boolean shuffle;

        public boolean isShuffle() {
            return shuffle;
        }

        public void setShuffle(boolean shuffle) {
            this.shuffle = shuffle;
        }
    }

    public static class Question {

        private boolean shuffle;
        private int count;
        private double succeedPercentage;

        public double getSucceedPercentage() {
            return succeedPercentage;
        }

        public void setSucceedPercentage(double succeedPercentage) {
            this.succeedPercentage = succeedPercentage;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isShuffle() {
            return shuffle;
        }

        public void setShuffle(boolean shuffle) {
            this.shuffle = shuffle;
        }
    }

}
