package subway;

import java.rmi.dgc.DGC;
import java.util.Scanner;
import subway.domain.Station;
import subway.graph.StationGraph;
import subway.view.Choice;
import subway.view.InputView;

public class Application {

    private static final StationGraph DISTANCE_GRAPH = new StationGraph();
    private static final StationGraph TIME_GRAPH = new StationGraph();

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Initializer.initializeStations(DISTANCE_GRAPH, TIME_GRAPH);

        while (InputView.readContinue(scanner)) {
            Choice choice = InputView.readChoice(scanner);
            if (choice == Choice.BACK) {
                continue;
            }
            Station from = InputView.readStation(scanner, "출발역");
            Station to = InputView.readStation(scanner, "도착역");
            //조회
        }
    }
}
