package pairmatching.config;

import java.util.Arrays;
import pairmatching.error.Error;

public enum Course implements NameSupplier {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Course ofName(String name) {
        return Arrays.stream(values())
            .filter(course -> course.name.equals(name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(Error.BAD_INPUT.message()));
    }
}
