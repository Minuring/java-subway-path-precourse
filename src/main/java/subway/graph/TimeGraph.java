package subway.graph;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Station;
import subway.domain.StationRepository;

public class TimeGraph {

    private static final WeightedMultigraph<Station, DefaultWeightedEdge> graph =
        new WeightedMultigraph(DefaultWeightedEdge.class);

    public static void synchronizeStations() {
        List<Station> stations = StationRepository.stations();
        stations.forEach(graph::addVertex);
    }

    public static void link(Station station1, Station station2, int time) {
        DefaultWeightedEdge edge = graph.addEdge(station1, station2);
        graph.setEdgeWeight(edge, time);
    }

    public static List<Station> getShortestPath(Station from, Station to) {
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        return dijkstraShortestPath.getPath(from, to).getVertexList();
    }

    public static int getShortestPathWeight(Station from, Station to) {
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        return (int)dijkstraShortestPath.getPath(from, to).getWeight();
    }
}
