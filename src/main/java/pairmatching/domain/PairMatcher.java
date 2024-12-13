package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.config.Course;
import pairmatching.config.Level;
import pairmatching.config.Mission;
import pairmatching.error.Error;

public class PairMatcher {

    private static Pairs PAIRS = Pairs.getInstance();

    private final List<Crew> backendCrews;
    private final List<Crew> frontendCrews;

    public PairMatcher(List<Crew> backendCrews, List<Crew> frontendCrews) {
        this.backendCrews = backendCrews;
        this.frontendCrews = frontendCrews;
    }

    public void match(Course course, Level level, Mission mission) {
        if (!mission.isLevel(level)) {
            throw new IllegalArgumentException(Error.LEVEL_NOT_MATCHED.message());
        }
        List<Pair> result = null;
        if (course == Course.BACKEND) {
            result = match(backendCrews, course, level);
        }
        if (course == Course.FRONTEND) {
            result = match(frontendCrews, course, level);
        }
        PAIRS.save(course, level, mission, result);
    }

    private List<Pair> match(List<Crew> crews, Course course, Level level) {
        List<String> names = crews.stream().map(Crew::getName).toList();
        List<String> shuffled = Randoms.shuffle(names);

        for (int sequence = 1; sequence <= 3; sequence++) {
            List<Pair> pairs = createPairs(crews, shuffled);
            if (hasDuplicatedPairInSameLevel(pairs, course, level)) {
                continue;
            }
            return pairs;
        }
        throw new IllegalStateException(Error.MATCH_FAILED.message());
    }


    private List<Pair> createPairs(List<Crew> crews, List<String> shuffled) {
        List<Pair> pairs = new ArrayList<>();
        int index = 0;
        int remains = shuffled.size();
        while (remains > 0) {
            String first = shuffled.get(index++);
            String second = shuffled.get(index++);
            String third = null;
            remains -= index;
            if (remains == 1) {
                third = shuffled.get(index);
            }
            pairs.add(createPair(crews, first, second, third));
        }
        return pairs;
    }

    private boolean hasDuplicatedPairInSameLevel(List<Pair> generatedPairs, Course course, Level level) {
        List<Mission> missions = Mission.ofLevel(level);
        for (Mission mission : missions) {
            List<Pair> savedPairs = PAIRS.getPairs(course, level, mission);

            boolean overlapped = savedPairs.stream()
                .anyMatch(savedPair -> generatedPairs.stream()
                    .anyMatch(savedPair::isOverlapped));
            if (overlapped) {
                return true;
            }
        }
        return false;
    }

    private Pair createPair(List<Crew> crews, String name1, String name2, String name3) {
        if (name3 == null) {
            return new Pair(crewOf(crews, name1), crewOf(crews, name2));
        }
        return new Pair(crewOf(crews, name1), crewOf(crews, name2), crewOf(crews, name3));
    }

    private Crew crewOf(List<Crew> crews, String name) {
        return crews.stream()
            .filter(crew -> crew.getName().equals(name))
            .findAny()
            .orElseThrow();
    }
}
