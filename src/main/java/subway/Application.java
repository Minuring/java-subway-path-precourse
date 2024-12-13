package subway;

import java.util.Scanner;
import subway.view.Choice;
import subway.view.InputView;

public class Application {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Initializer.initializeStations();

        while (InputView.readContinue(scanner)) {
            Choice choice = InputView.readChoice(scanner);
            if (choice == Choice.BACK) {
                continue;
            }
            //조회
        }
    }
}
