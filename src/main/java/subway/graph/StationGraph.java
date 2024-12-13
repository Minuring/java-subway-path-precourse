package subway.graph;

import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.error.Error;

public class StationGraph {

    private final WeightedMultigraph<Station, DefaultWeightedEdge> graph =
        new WeightedMultigraph<>(DefaultWeightedEdge.class);

    public void synchronizeStations() {
        List<Station> stations = StationRepository.stations();
        stations.forEach(graph::addVertex);
    }

    public void link(Station station1, Station station2, int weight) {
        graph.setEdgeWeight(graph.addEdge(station1, station2), weight);
    }

    public List<Station> getShortestPath(Station from, Station to) {
        GraphPath path = getPath(from, to);
        return path.getVertexList();
    }

    public List getShortestPathEdges(Station from, Station to) {
        GraphPath path = getPath(from, to);
        return path.getEdgeList();
    }

    public int getShortestPathWeight(Station from, Station to) {
        GraphPath path = getPath(from, to);
        return (int) path.getWeight();
    }

    private GraphPath getPath(Station from, Station to) {
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        GraphPath path = dijkstraShortestPath.getPath(from, to);
        if (path == null) {
            throw new IllegalArgumentException(Error.UNREACHABLE.message());
        }
        return path;
    }
}
