package subway;

import java.util.List;
import java.util.Scanner;
import subway.domain.Station;
import subway.error.Error;
import subway.graph.EdgeRepository;
import subway.graph.GraphAdvice;
import subway.graph.StationGraph;
import subway.view.Choice;
import subway.view.InputView;
import subway.view.OutputView;
import subway.view.OutputView.Result;

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

            if (from.equals(to)) {
                OutputView.printErrorMessage(Error.SAME_STATION.message());
                continue;
            }
            query(choice, from, to);
        }
    }

    private static void query(Choice choice, Station from, Station to) {
        if (choice == Choice.DISTANCE) {
            query(DISTANCE_GRAPH, from, to);
        }
        if (choice == Choice.TIME) {
            query(TIME_GRAPH, from, to);
        }
    }

    private static void query(StationGraph graph, Station from, Station to) {
        List<Station> path = graph.getShortestPath(from, to);
        int totalTime = GraphAdvice.calculatePathTime(path);
        int totalDistance = GraphAdvice.calculatePathDistance(path);
        OutputView.printResult(new Result(totalDistance, totalTime, path));
    }
}
