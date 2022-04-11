import java.util.Map;

public class SimulationResult {
    private String key;
    private Map<Integer, Double> timeElapsedInPositionQueue;
    private Double timeElapsed;

    public SimulationResult(String key, Map<Integer, Double> timeElapsedInPositionQueue, Double timeElapsed) {
        this.key = key;
        this.timeElapsedInPositionQueue = timeElapsedInPositionQueue;
        this.timeElapsed = timeElapsed;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<Integer, Double> getTimeElapsedInPositionQueue() {
        return timeElapsedInPositionQueue;
    }

    public void setTimeElapsedInPositionQueue(Map<Integer, Double> timeElapsedInPositionQueue) {
        this.timeElapsedInPositionQueue = timeElapsedInPositionQueue;
    }

    public Double getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(Double timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}
