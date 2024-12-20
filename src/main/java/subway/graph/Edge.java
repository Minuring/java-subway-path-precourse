package subway.graph;

import subway.domain.Station;

public class Edge {

    private Station from;
    private Station to;
    private final int distance;
    private final int time;

    public Edge(Station from, Station to, int distance, int time) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.time = time;
    }

    public Station getFrom() {
        return from;
    }

    public Station getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
