package subway.graph;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import subway.Initializer;
import subway.domain.Station;
import subway.domain.StationRepository;

class StationGraphTest {

    StationGraph distanceGraph = new StationGraph();
    StationGraph timeGraph = new StationGraph();

    Station gyodae;
    Station yangjaeForest;

    @BeforeEach
    void setUp() {
        Initializer.initializeStations(distanceGraph, timeGraph);
        gyodae = StationRepository.getByName("교대역");
        yangjaeForest = StationRepository.getByName("양재시민의숲역");
    }

    @Test
    void distanceTest() {
        List<Station> paths = distanceGraph.getShortestPath(gyodae, yangjaeForest);

        assertThat(paths).containsExactly(
            gyodae,
            StationRepository.getByName("강남역"),
            StationRepository.getByName("양재역"),
            yangjaeForest
        );

        int weight = GraphAdvice.calculatePathDistance(paths);
        assertThat(weight).isEqualTo(14);
    }

    @Test
    void timeTest() {
        List<Station> paths = timeGraph.getShortestPath(gyodae, yangjaeForest);

        assertThat(paths).containsExactly(
            gyodae,
            StationRepository.getByName("남부터미널역"),
            StationRepository.getByName("양재역"),
            yangjaeForest
        );

        int weight = GraphAdvice.calculatePathTime(paths);
        assertThat(weight).isEqualTo(10);
    }
}