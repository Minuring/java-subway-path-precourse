package subway;

import static subway.domain.StationRepository.addStation;

import java.util.ArrayList;
import java.util.List;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.graph.Edge;
import subway.graph.EdgeRepository;
import subway.graph.StationGraph;

public class Initializer {

    static {
        addStation(new Station("교대역"));
        addStation(new Station("강남역"));
        addStation(new Station("역삼역"));
        addStation(new Station("남부터미널역"));
        addStation(new Station("양재역"));
        addStation(new Station("양재시민의숲역"));
        addStation(new Station("매봉역"));
    }

    public static void initializeStations(StationGraph distanceGraph, StationGraph timeGraph) {
        distanceGraph.synchronizeStations();
        timeGraph.synchronizeStations();
        List<Edge> edges = createEdges();
        EdgeRepository.addAllEdges(edges);
        for (Edge edge : edges) {
            distanceGraph.link(edge.getFrom(), edge.getTo(), edge.getDistance());
            timeGraph.link(edge.getFrom(), edge.getTo(), edge.getTime());
        }
    }

    private static List<Edge> createEdges() {
        List<Edge> edges = new ArrayList<>();
        edges.add(createEdge("교대역", "강남역", 2, 3));
        edges.add(createEdge("강남역", "역삼역", 2, 3));
        edges.add(createEdge("교대역", "남부터미널역", 3, 2));
        edges.add(createEdge("남부터미널역", "양재역", 6, 5));
        edges.add(createEdge("양재역", "매봉역", 1, 1));
        edges.add(createEdge("강남역", "양재역", 2, 8));
        edges.add(createEdge("양재역", "양재시민의숲역", 10, 3));
        return edges;
    }

    private static Edge createEdge(String name1, String name2, int distance, int time) {
        Station station1 = StationRepository.getByName(name1);
        Station station2 = StationRepository.getByName(name2);
        return new Edge(station1, station2, distance, time);
    }
}
