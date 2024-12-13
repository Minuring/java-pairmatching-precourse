package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import pairmatching.config.Course;
import pairmatching.config.Level;
import pairmatching.config.Mission;

public class Pairs {

    private static final Pairs INSTANCE = new Pairs();

    private Map<Course, Map<Level, Map<Mission, List<Pair>>>> pairs = new LinkedHashMap<>();

    private Pairs() {
        reset();
    }

    public static Pairs getInstance() {
        return INSTANCE;
    }

    public void reset() {
        pairs = new LinkedHashMap<>();
        Arrays.stream(Course.values()).forEach(course -> {
            Map<Level, Map<Mission, List<Pair>>> levelMissions = new LinkedHashMap<>();

            Arrays.stream(Level.values()).forEach(level -> {
                Map<Mission, List<Pair>> missionPairs = new LinkedHashMap<>();

                Mission.ofLevel(level)
                    .forEach(mission -> missionPairs.put(mission, new ArrayList<>()));

                levelMissions.put(level, missionPairs);
            });

            pairs.put(course, levelMissions);
        });
    }

    public boolean hasMatched(Course course, Level level, Mission mission) {
        List<Pair> pairs = getPairs(course, level, mission);
        return !pairs.isEmpty();
    }

    public List<Pair> getPairs(Course course, Level level, Mission mission) {
        Map<Level, Map<Mission, List<Pair>>> levelMissions = pairs.get(course);
        Map<Mission, List<Pair>> missionPairs = levelMissions.get(level);
        return missionPairs.get(mission);
    }

    public void save(Course course, Level level, Mission mission, List<Pair> result) {
        Map<Level, Map<Mission, List<Pair>>> levelMissions = this.pairs.get(course);
        Map<Mission, List<Pair>> missionPairs = levelMissions.get(level);
        missionPairs.replace(mission, result);
    }
}
