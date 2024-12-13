package subway.graph;

import java.util.List;
import java.util.function.BiFunction;
import subway.domain.Station;

public class GraphAdvice {

    public static int calculatePathDistance(List<Station> path) {
        return calculatePath(path, EdgeRepository::calculateDistanceBetween);
    }

    public static int calculatePathTime(List<Station> path) {
        return calculatePath(path, EdgeRepository::calculateTimeBetween);
    }

    private static int calculatePath(List<Station> path,
        BiFunction<Station, Station, Integer> function) {
        int sum = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Station station1 = path.get(i);
            Station station2 = path.get(i + 1);
            sum += function.apply(station1, station2);
        }
        return sum;
    }
}
