package space.efremov.otus.spring.domain;

import lombok.Data;

import java.util.List;

public class Question {
    private String text;
    private Double weight;
    private QuestionType type;
    private List<Answer> answers;

    public Question() {
    }

    public Question(String text, Double weight, QuestionType type, List<Answer> answers) {
        this.text = text;
        this.weight = weight;
        this.type = type;
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
