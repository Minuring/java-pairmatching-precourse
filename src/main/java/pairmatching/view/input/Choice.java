package pairmatching.view.input;

import java.util.Arrays;
import pairmatching.error.Error;

public enum Choice {
    MATCH("1"),
    QUERY("2"),
    RESET("3"),
    QUIT("Q");

    private final String input;

    Choice(String input) {
        this.input = input;
    }

    public static Choice of(String input) {
        return Arrays.stream(values())
            .filter(choice -> choice.input.equals(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(Error.BAD_INPUT.message()));
    }
}
