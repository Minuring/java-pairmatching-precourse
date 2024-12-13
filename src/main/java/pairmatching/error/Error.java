package pairmatching.error;

import java.util.Arrays;
import java.util.IllegalFormatException;

public enum Error {
    BAD_INPUT("잘못된 입력입니다. 다시 입력해 주세요."),
    LEVEL_NOT_MATCHED("해당 레벨에는 해당 미션이 없습니다."),
    MATCH_FAILED("3회 시도까지 매칭이 되지않아 매칭에 실패했습니다.");

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
