package Scheduler;

import Simulation.Event;

public class SchedulerItem {
    private Event event;
    private Double time;
    private Boolean used;
    private Double calculatedTime;

    public SchedulerItem(Event event, Double time) {
        this.time = time;
        this.event = event;
        this.calculatedTime = time;
    }

    public SchedulerItem(Integer minValue, Integer maxValue, Double randomNum, Double time, Event event) {
        this.event = event;
        this.time = calculateTime(minValue, maxValue, randomNum, time);
        this.used = false;
    }

    private Double calculateTime(Integer minValue, Integer maxValue, Double randomNum, Double time) {
        Double timeCalculated = (minValue + ((maxValue - minValue) * randomNum));
        this.calculatedTime = timeCalculated;
        return timeCalculated + time;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Double getTime() {
        return this.time;
    }

    public Event getEvent() {
        return this.event;
    }

    public Double getCalculatedTime() {
        return this.calculatedTime;
    }
}
