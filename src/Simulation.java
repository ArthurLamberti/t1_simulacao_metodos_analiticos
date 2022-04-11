import Scheduler.*;
import YmlReader.SimulationConfig;
import Simulation.*;
import YmlReader.State;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation {
    private SimulationConfig simulationConfig;
    private List<Double> randomNumList;
    private Integer actualQueueSize;
    private Double timeExecution;
    private Map<Integer, Double> queueState;
    private Scheduler scheduler;

    public Simulation() {
    }

    public SimulationResult run(Integer iterationId, String key) {
        while (!this.randomNumList.isEmpty()) {
            Double nextRandom = randomNumList.remove(0);

            SchedulerItem schedulerItem = scheduler.getNext();
            if (Event.ARRIVAL.equals(schedulerItem.getEvent())) {
                updateTime(schedulerItem.getTime(), this.actualQueueSize, schedulerItem.getEvent(), schedulerItem.getCalculatedTime());
                if (this.actualQueueSize < simulationConfig.getQueueCapacity()) {
                    this.actualQueueSize++;
                    if (this.actualQueueSize <= 1) { //AGENDA SAIDA
                        scheduler.addItem(
                                simulationConfig.getInitialServiceTime(),
                                simulationConfig.getFinalServiceTime(),
                                nextRandom,
                                this.timeExecution,
                                Event.EXIT
                        );
                        nextRandom = randomNumList.remove(0);
                    }
                }
                scheduler.addItem(
                        simulationConfig.getInitialArrival(),
                        simulationConfig.getFinalArrival(),
                        nextRandom,
                        this.timeExecution,
                        Event.ARRIVAL
                );
            } else if (Event.EXIT.equals(schedulerItem.getEvent())) {
                updateTime(schedulerItem.getTime(), this.actualQueueSize, schedulerItem.getEvent(), schedulerItem.getCalculatedTime());
                this.actualQueueSize--;
                if (this.actualQueueSize >= 1) {
                    scheduler.addItem(
                            simulationConfig.getInitialServiceTime(),
                            simulationConfig.getFinalServiceTime(),
                            nextRandom,
                            this.timeExecution,
                            Event.EXIT
                    );
                }
            }

        }

        System.out.println("acabou numeros aleatorios");
        System.out.println("Tempo em cada quantidade na fila");
        for (int i = 0; i <= this.simulationConfig.getQueueCapacity(); i++) {
            System.out.println(this.queueState.get(i));
        }
        return new SimulationResult(key+"-"+iterationId, this.queueState, this.timeExecution);
    }

    public void config(SimulationConfig simulationConfig, List<Double> randomNumList) {
        this.simulationConfig = simulationConfig;
        this.randomNumList = randomNumList;
        actualQueueSize = 0;
        timeExecution = 0.0;
        queueState = new HashMap<>();
        this.scheduler = new Scheduler(getInitialEvent(simulationConfig.getState()), simulationConfig.getStateTime());
        for (int i = 0; i <= simulationConfig.getQueueCapacity(); i++) {
            queueState.put(i, 0.0);
        }
    }

    private void updateTime(Double eventTime, Integer nextQueueSize, Event event, Double calculatedTime) {
        Double currentTimeCapacity = queueState.get(actualQueueSize);
        Double timeToSum = 0D;
        if(currentTimeCapacity.compareTo(0D) == 0 && event.equals(Event.ARRIVAL)){
            timeToSum += calculatedTime;
        } else {
            timeToSum = eventTime - this.timeExecution + currentTimeCapacity;
        }
        queueState.replace(this.actualQueueSize, timeToSum);

        this.timeExecution = eventTime;
        this.actualQueueSize = nextQueueSize;
    }

    private Event getInitialEvent(State state) {
        if (Event.ARRIVAL.name().equals(state.name())) {
            return Event.ARRIVAL;
        } else if (Event.EXIT.name().equals(state.name())) {
            return Event.EXIT;
        }
        return Event.EMPTY;
    }
}
