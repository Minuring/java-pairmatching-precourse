package pairmatching.config;

public enum Mission {
    RACING(Level.LEVEL1),
    LOTTO(Level.LEVEL1),
    BASEBALL(Level.LEVEL1),

    BASKET(Level.LEVEL2),
    ORDER(Level.LEVEL2),
    SUBWAY(Level.LEVEL2),

    PERFORMANCE(Level.LEVEL4),
    DEPLOY(Level.LEVEL4);

    private final Level level;

    Mission(Level level) {
        this.level = level;
    }
}
