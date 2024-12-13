package subway;

import static subway.domain.StationRepository.addStation;
import static subway.domain.StationRepository.getByName;

import subway.domain.Station;
import subway.graph.DistanceGraph;

public class Initializer {

    public static void initializeStations() {
        addStation(new Station("교대역"));
        addStation(new Station("강남역"));
        addStation(new Station("역삼역"));
        addStation(new Station("남부터미널역"));
        addStation(new Station("양재역"));
        addStation(new Station("양재시민의숲역"));
        addStation(new Station("매봉역"));
        DistanceGraph.synchronizeStations();
        linkAll();
    }

    private static void linkAll() {
        linkTogether("교대역", "강남역", 2);
        linkTogether("강남역", "역삼역", 2);
        linkTogether("교대역", "남부터미널역", 3);
        linkTogether("남부터미널역", "양재역", 6);
        linkTogether("양재역", "매봉역", 1);
        linkTogether("강남역", "양재역", 2);
        linkTogether("양재역", "양재시민의숲역", 10);
    }

    private static void linkTogether(String station1, String station2, int distance) {
        DistanceGraph.link(getByName(station1), getByName(station2), distance);
        DistanceGraph.link(getByName(station2), getByName(station1), distance);
    }
}
