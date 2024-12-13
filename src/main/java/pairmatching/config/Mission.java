package pairmatching.config;

import java.util.Arrays;
import java.util.List;

public enum Mission implements NameSupplier {
    RACING(Level.LEVEL1, "자동차경주"),
    LOTTO(Level.LEVEL1, "로또"),
    BASEBALL(Level.LEVEL1, "숫자야구게임"),

    BASKET(Level.LEVEL2, "장바구니"),
    ORDER(Level.LEVEL2, "결제"),
    SUBWAY(Level.LEVEL2, "지하철노선도"),

    PERFORMANCE(Level.LEVEL4, "성능개선"),
    DEPLOY(Level.LEVEL4, "배포");

    private final Level level;
    private final String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<Mission> ofLevel(Level level) {
        return Arrays.stream(values())
            .filter(mission -> mission.level == level)
            .toList();
    }
}
