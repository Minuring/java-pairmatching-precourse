package pairmatching.domain;

import java.util.List;

public class Pair {

    private final List<Crew> crews;

    public Pair(List<Crew> crews) {
        this.crews = crews;
    }

    @Override
    public String toString() {
        List<String> names = crews.stream().map(Crew::toString).toList();
        return String.join(" : ", names);
    }
}
