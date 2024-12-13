package subway.error;

import java.util.Arrays;
import java.util.IllegalFormatException;

public enum Error {
    INPUT_FORMAT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    BAD_INPUT("잘못된 입력입니다. 다시 입력해 주세요."),
    STATION_NOT_FOUND("존재하지 않는 역명입니다. 다시 입력해 주세요."),
    UNREACHABLE("출발역과 도착역이 연결되어 있지 않습니다."),
    SAME_STATION("출발역과 도착역이 동일합니다.");

    private static final String PREFIX = "[ERROR] ";

    private final String message;

    Error(String message) {
        this.message = message;
    }

    public String message(Object... args) {
        try {
            return String.format(PREFIX + message, args);
        } catch (IllegalFormatException e) {
            System.err.printf("[message] %s [args] %s", message, Arrays.toString(args));
            throw e;
        }
    }
}
