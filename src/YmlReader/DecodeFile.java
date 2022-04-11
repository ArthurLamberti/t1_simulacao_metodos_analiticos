package YmlReader;

import java.util.*;

public class DecodeFile {
    public DecodeFile(){}

    public Map<String, SimulationConfig> decode(String textToDecode){
        Map<String, SimulationConfig> mapSimulation = new HashMap<>();

        List<String> lines = Arrays.asList(textToDecode.split("\\n"));
        System.out.println("Lendo linhas");
        lines.forEach(line -> {
            SimulationConfig simulation = new SimulationConfig();
            if(line.startsWith("#") || line.isBlank()){
                return;
            }
            String[] lineParts = line.split("\\.");

            if(mapSimulation.containsKey(lineParts[0])){//se ja tem o objeto no map
                simulation = mapSimulation.get(lineParts[0]);
                setSimulationValue(lineParts[1], simulation);
            } else {
                setSimulationValue(lineParts[1],simulation);
                mapSimulation.put(lineParts[0],simulation);
            }
        });
        return mapSimulation;
    }

    private void setSimulationValue(String linePart, SimulationConfig simulation) {
        String lineKey = linePart.split(":")[0].trim();
        String lineValue = linePart.split(":")[1].trim();
        switch (lineKey) {
            case "value_multiplier":
                simulation.setMultiplier(Integer.valueOf(lineValue));
                break;
            case "value_increment":
                simulation.setIncrement(Integer.valueOf(lineValue));
                break;
            case "value_mod":
                simulation.setMod(Integer.valueOf(lineValue));
                break;
            case "queue_capacity":
                simulation.setQueueCapacity(Integer.valueOf(lineValue));
                break;
            case "servers_quantity":
                simulation.setServerQuantity(Integer.valueOf(lineValue));
                break;
            case "value_initial_arrival":
                simulation.setInitialArrival(Integer.valueOf(lineValue));
                break;
            case "value_final_arrival":
                simulation.setFinalArrival(Integer.valueOf(lineValue));
                break;
            case "value_initial_service_time":
                simulation.setInitialServiceTime(Integer.valueOf(lineValue));
                break;
            case "value_final_service_time":
                simulation.setFinalServiceTime(Integer.valueOf(lineValue));
                break;
            case "initial_state":
                simulation.setState(decodeState(lineValue));
                break;
            case "state_time":
                simulation.setStateTime(Double.valueOf(lineValue));
                break;
            case "list_seed":
                simulation.setListSeeds(decodeListSeed(lineValue));
                break;
            default:
                System.out.println("Incorrect key: " + lineKey);
        }
    }

    private List<Integer> decodeListSeed(String lineValue) {
        List<Integer> seeds = new ArrayList<>();
        String[] values = lineValue.split(",");
        for (int i = 0; i < values.length; i++) {
            seeds.add(Integer.valueOf(values[i].trim()));
        }
        return seeds;
    }

    private State decodeState(String lineValue) {
        if(State.ARRIVAL.name().equalsIgnoreCase(lineValue)){
            return State.ARRIVAL;
        } else if (State.EXIT.name().equalsIgnoreCase(lineValue)){
            return State.EXIT;
        }
        return State.EMPTY;
    }

}
