package pairmatching.view;

import pairmatching.error.Error;
import java.util.function.IntPredicate;

public class ViewUtils {

    public static boolean parseYN(String input) {
        if (input.equals("Y")) {
            return true;
        }
        if (input.equals("N")) {
            return false;
        }
        throw new IllegalArgumentException(Error.BAD_INPUT.message());
    }

    public static void validateDoesNotContainsAnyWhiteSpace(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException(Error.BAD_INPUT.message());
        }
    }

    public static void validateAllCharsMatched(String input, IntPredicate predicate) {
        if (input.chars().allMatch(predicate)) {
            return;
        }
        throw new IllegalArgumentException(Error.BAD_INPUT.message());
    }

    public static void validateMatchesPattern(String input, String patternRegex) {
        if (input.matches(patternRegex)) {
            return;
        }
        throw new IllegalArgumentException(Error.BAD_INPUT.message());
    }
}
