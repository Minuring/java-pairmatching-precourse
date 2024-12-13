package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair {

    private final List<Crew> crews = new ArrayList<>();

    public Pair(Crew first, Crew second) {
        crews.add(first);
        crews.add(second);
    }

    public Pair(Crew first, Crew second, Crew third) {
        crews.add(first);
        crews.add(second);
        crews.add(third);
    }

    public boolean isOverlapped(Pair otherPair) {
        int count = 0;
        for (Crew thisCrew : this.crews) {
            if (otherPair.crews.contains(thisCrew)) {
                count += 1;
            }
        }
        return count >= 2;
    }

    public List<Crew> getCrews() {
        return Collections.unmodifiableList(crews);
    }

    @Override
    public String toString() {
        List<String> names = crews.stream().map(Crew::getName).toList();
        return String.join(" : ", names);
    }
}
