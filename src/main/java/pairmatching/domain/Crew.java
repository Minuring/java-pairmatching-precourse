package pairmatching.domain;

import pairmatching.config.Course;

public class Crew {

    private final Course course;
    private final String name;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}