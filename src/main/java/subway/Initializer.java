package subway;

import static subway.domain.StationRepository.addStation;

import subway.domain.Station;
import subway.domain.StationRepository;
import subway.graph.DistanceGraph;
import subway.graph.TimeGraph;

public class Initializer {

    public static void initializeStations() {
        registerAllStations();
        DistanceGraph.synchronizeStations();
        TimeGraph.synchronizeStations();
        linkAllDistanceGraph();
        linkAllTimeGraph();
    }

    private static void registerAllStations() {
        addStation(new Station("교대역"));
        addStation(new Station("강남역"));
        addStation(new Station("역삼역"));
        addStation(new Station("남부터미널역"));
        addStation(new Station("양재역"));
        addStation(new Station("양재시민의숲역"));
        addStation(new Station("매봉역"));
    }

    private static void linkAllDistanceGraph() {
        linkDistanceTogether("교대역", "강남역", 2);
        linkDistanceTogether("강남역", "역삼역", 2);
        linkDistanceTogether("교대역", "남부터미널역", 3);
        linkDistanceTogether("남부터미널역", "양재역", 6);
        linkDistanceTogether("양재역", "매봉역", 1);
        linkDistanceTogether("강남역", "양재역", 2);
        linkDistanceTogether("양재역", "양재시민의숲역", 10);
    }

    private static void linkDistanceTogether(String station1, String station2, int distance) {
        Station stationA = StationRepository.getByName(station1);
        Station stationB = StationRepository.getByName(station2);
        DistanceGraph.link(stationA, stationB, distance);
        DistanceGraph.link(stationB, stationA, distance);
    }

    private static void linkAllTimeGraph() {
        linkTimeTogether("교대역", "강남역", 3);
        linkTimeTogether("강남역", "역삼역", 3);
        linkTimeTogether("교대역", "남부터미널역", 2);
        linkTimeTogether("남부터미널역", "양재역", 5);
        linkTimeTogether("양재역", "매봉역", 1);
        linkTimeTogether("강남역", "양재역", 8);
        linkTimeTogether("양재역", "양재시민의숲역", 3);
    }

    private static void linkTimeTogether(String station1, String station2, int distance) {
        Station stationA = StationRepository.getByName(station1);
        Station stationB = StationRepository.getByName(station2);
        TimeGraph.link(stationA, stationB, distance);
        TimeGraph.link(stationB, stationA, distance);
    }
}
