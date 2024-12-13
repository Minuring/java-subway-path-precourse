package subway.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import subway.domain.Station;
import subway.error.Error;

public class EdgeRepository {

    private static final List<Edge> edges = new ArrayList<>();

    public static void addAllEdges(List<Edge> edges) {
        EdgeRepository.edges.addAll(edges);
    }

    public static int calculateDistanceBetween(Station station1, Station station2) {
        return calculateBetween(station1, station2, Edge::getDistance);
    }

    public static int calculateTimeBetween(Station station1, Station station2) {
        return calculateBetween(station1, station2, Edge::getTime);
    }

    private static int calculateBetween(Station station1, Station station2,
        Function<Edge, Integer> function) {
        //Graph로부터 경로를 얻고 사용한다고 가정. 즉 두 역은 연결되어있다고 가정
        for (Edge edge : edges) {
            if (edge.getFrom().equals(station1) && edge.getTo().equals(station2)) {
                return function.apply(edge);
            }
        }
        throw new IllegalArgumentException(Error.UNREACHABLE.message());
    }
}
