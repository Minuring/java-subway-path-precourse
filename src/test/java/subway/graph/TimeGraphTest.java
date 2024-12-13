package subway.graph;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import subway.Initializer;
import subway.domain.Station;
import subway.domain.StationRepository;

class TimeGraphTest {

    @BeforeAll
    static void setUp() {
        Initializer.initializeStations();
    }

    @Test
    void distanceTest() {
        Station gyodae = StationRepository.getByName("교대역");
        Station yangjaeForest = StationRepository.getByName("양재시민의숲역");

        List<Station> paths = TimeGraph.getShortestPath(gyodae, yangjaeForest);

        assertThat(paths).containsExactly(
            gyodae,
            StationRepository.getByName("남부터미널역"),
            StationRepository.getByName("양재역"),
            yangjaeForest
        );

        int weight = TimeGraph.getShortestPathWeight(gyodae, yangjaeForest);
        assertThat(weight).isEqualTo(10);
    }
}