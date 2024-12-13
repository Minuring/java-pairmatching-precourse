package pairmatching.file;

import pairmatching.domain.Crew;
import pairmatching.config.Course;

public class CourseCrewLoader extends FileLoader<Crew> {

    private final Course course;

    public CourseCrewLoader(Course course, String path) {
        super(path);
        this.course = course;
    }

    @Override
    protected Crew bindLine(String line) {
        String name = line.trim();
        return new Crew(course, name);
    }
}
