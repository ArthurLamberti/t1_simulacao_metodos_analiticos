import YmlReader.SimulationConfig;

import java.util.ArrayList;
import java.util.List;

public class RandomNumGenerator {
    public RandomNumGenerator() {
    }

    public List<Double> generate(Integer amount, SimulationConfig simulationConfig) {
        List<Double> randomList = new ArrayList<>();
        Integer lastGenerated = simulationConfig.getSeed();
        for (int i = 0; i < amount; i++) {
            lastGenerated = getRandom(
                    lastGenerated,
                    simulationConfig.getMultiplier(),
                    simulationConfig.getIncrement(),
                    simulationConfig.getMod());
            Double lastGeneratedInDouble = getValueBetween0And1(lastGenerated,simulationConfig.getMod());
            randomList.add(lastGeneratedInDouble);
        }

        return randomList;
    }

    private Integer getRandom(Integer lastGenerated, Integer multiplier, Integer increment, Integer mod) {
        return (lastGenerated * multiplier + increment) % mod;
    }

    private Double getValueBetween0And1(Integer lastGenerated, Integer mod){
        return lastGenerated / Double.valueOf(mod);
    }
}
