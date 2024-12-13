package subway.view;

import java.util.Scanner;
import java.util.function.Function;
import subway.error.Error;

public class InputView {

    public static boolean readContinue(Scanner scanner) {
        return readUntilSuccess(scanner,
            () -> System.out.println("## 메인 화면\n1. 경로 조회\nQ. 종료\n\n## 원하는 기능을 선택하세요."),
            input -> {
                if (input.equals("1")) {
                    return true;
                }
                if (input.equals("Q")) {
                    return false;
                }
                throw new IllegalArgumentException(Error.INPUT_FORMAT.message());
            });
    }

    private static <T> T readUntilSuccess(Scanner scanner, Runnable printable,
        Function<String, T> function) {
        while (true) {
            try {
                printable.run();
                String input = scanner.nextLine().trim();
                return function.apply(input);
            } catch (IllegalArgumentException | IllegalStateException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
