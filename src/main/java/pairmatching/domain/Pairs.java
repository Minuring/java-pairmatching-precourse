package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import pairmatching.config.Course;
import pairmatching.config.Level;

public class Pairs {

    private static final Pairs INSTANCE = new Pairs();

    private Map<Course, Map<Level, List<Pair>>> pairs = new LinkedHashMap<>();

    private Pairs() {
        reset();
    }

    public static Pairs getInstance() {
        return INSTANCE;
    }

    public void reset() {
        pairs = new LinkedHashMap<>();
        Arrays.stream(Course.values()).forEach(course -> {
            Map<Level, List<Pair>> levelPairs = new LinkedHashMap<>();

            Arrays.stream(Level.values()).forEach(level -> {
                levelPairs.put(level, new ArrayList<>());
            });
            pairs.put(course, levelPairs);
        });
    }
}
