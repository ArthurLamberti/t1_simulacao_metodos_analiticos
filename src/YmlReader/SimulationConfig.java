package YmlReader;

import java.util.List;

public class SimulationConfig {
    private Integer multiplier;
    private Integer increment;
    private Integer mod;
    private Integer queueCapacity;
    private Integer serverQuantity;
    private Integer initialArrival;
    private Integer finalArrival;
    private Integer initialServiceTime;
    private Integer finalServiceTime;
    private State state;
    private Double stateTime;
    private List<Integer> listSeeds;

    public SimulationConfig() {
    }

    public Integer getSeed() {
        return listSeeds.remove(0);
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
    }

    public Integer getIncrement() {
        return increment;
    }

    public void setIncrement(Integer increment) {
        this.increment = increment;
    }

    public Integer getMod() {
        return mod;
    }

    public void setMod(Integer mod) {
        this.mod = mod;
    }

    public Integer getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public Integer getServerQuantity() {
        return serverQuantity;
    }

    public void setServerQuantity(Integer serverQuantity) {
        this.serverQuantity = serverQuantity;
    }

    public Integer getInitialArrival() {
        return initialArrival;
    }

    public void setInitialArrival(Integer initialArrival) {
        this.initialArrival = initialArrival;
    }

    public Integer getFinalArrival() {
        return finalArrival;
    }

    public void setFinalArrival(Integer finalArrival) {
        this.finalArrival = finalArrival;
    }

    public Integer getInitialServiceTime() {
        return initialServiceTime;
    }

    public void setInitialServiceTime(Integer initialServiceTime) {
        this.initialServiceTime = initialServiceTime;
    }

    public Integer getFinalServiceTime() {
        return finalServiceTime;
    }

    public void setFinalServiceTime(Integer finalServiceTime) {
        this.finalServiceTime = finalServiceTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Double getStateTime() {
        return stateTime;
    }

    public void setStateTime(Double stateTime) {
        this.stateTime = stateTime;
    }


    public List<Integer> getListSeeds() {
        return listSeeds;
    }

    public void setListSeeds(List<Integer> listSeeds) {
        this.listSeeds = listSeeds;
    }
}
