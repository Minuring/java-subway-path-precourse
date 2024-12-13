package subway;

import java.util.Scanner;
import subway.view.InputView;

public class Application {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Initializer.initializeStations();

        while (InputView.readContinue(scanner)) {
            //
        }
    }
}
