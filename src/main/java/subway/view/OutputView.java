package subway.view;

import java.util.List;
import subway.domain.Station;

public class OutputView {

    public static void printErrorMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public static void printResult(Result result) {
        System.out.println("## 조회 결과");
        System.out.println("[INFO] ---");
        System.out.printf("[INFO] 총 거리: %dkm\n", result.distance);
        System.out.printf("[INFO] 총 소요 시간: %d분\n", result.time);
        System.out.println("[INFO] ---");
        result.stations.forEach(station -> System.out.printf("[INFO] %s\n", station.getName()));
        System.out.println();
    }

    public static class Result {

        final int distance;
        final int time;
        final List<Station> stations;

        public Result(int distance, int time, List<Station> stations) {
            this.distance = distance;
            this.time = time;
            this.stations = stations;
        }
    }
}
