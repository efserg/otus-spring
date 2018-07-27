package space.efremov.otus.spring.domain;

import lombok.Data;

@Data
public class Student {
    private final String name;
    private final String lastname;

    @Override
    public String toString() {
        return name + " " + lastname;
    }
}
