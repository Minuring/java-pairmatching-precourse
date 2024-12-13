package pairmatching.config;

import java.util.Arrays;
import pairmatching.error.Error;

public enum Level implements NameSupplier {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Level ofName(String name) {
        return Arrays.stream(values())
            .filter(level -> level.name.equals(name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(Error.BAD_INPUT.message()));
    }
}
