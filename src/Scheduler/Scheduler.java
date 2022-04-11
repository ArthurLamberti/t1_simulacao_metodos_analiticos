package Scheduler;

import Simulation.Event;
import YmlReader.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Scheduler {
    List<SchedulerItem> schedulerItemList;
    List<SchedulerItem> schedulerItemListHistory;

    public Scheduler(Event event, Double stateTime) {
        SchedulerItem schedulerItem = new SchedulerItem(event, stateTime);
        this.schedulerItemList = new ArrayList<>();
        this.schedulerItemListHistory = new ArrayList<>();
        schedulerItemList.add(schedulerItem);
        schedulerItemListHistory.add(schedulerItem);
    }

    public void addItem(Integer minValue, Integer maxValue, Double randomNum, Double time, Event event){
        SchedulerItem schedulerItem = new SchedulerItem(
                minValue,
                maxValue,
                randomNum,
                time,
                event
        );
        schedulerItemList.add(schedulerItem);
        schedulerItemList.sort(Comparator.comparing((SchedulerItem item) -> item.getTime()));
        schedulerItemListHistory.add(schedulerItem);
    }

    public SchedulerItem getNext(){
        return schedulerItemList.remove(0);
    }

    public void print() {
        System.out.println("===================TEMPO COM SORT=====================");
        this.schedulerItemList.forEach(item -> System.out.println(item.getTime()));
        System.out.println("===================TEMPO SEM SORT=====================");
        this.schedulerItemListHistory.forEach(item -> System.out.println(item.getTime()));
    }
}
