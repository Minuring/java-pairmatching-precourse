package pairmatching.error;

import java.util.Arrays;
import java.util.IllegalFormatException;

public enum Error {
    BAD_INPUT("잘못된 입력입니다. 다시 입력해 주세요.");

    private final String message;

    Error(String message) {
        this.message = message;
    }

    public String message(Object... args) {
        try {
            return String.format(message, args);
        } catch (IllegalFormatException e) {
            System.err.printf("[message] %s [args] %s", message, Arrays.toString(args));
            throw e;
        }
    }
}
