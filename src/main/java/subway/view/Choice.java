package subway.view;

import java.util.Arrays;
import subway.error.Error;

public enum Choice {
    DISTANCE("1"),
    TIME("2"),
    BACK("B");

    private final String input;

    Choice(String input) {
        this.input = input;
    }

    public static Choice from(String input) {
        return Arrays.stream(values())
            .filter(choice -> choice.input.equals(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(Error.INPUT_FORMAT.message()));
    }
}
