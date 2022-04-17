import YmlReader.YmlReader;
import YmlReader.SimulationConfig;

import java.io.*;
import java.util.*;

public class Main {
    //    private static final Integer RANDOM_NUM_AMOUNT = 100000;
    private static final Integer RANDOM_NUM_AMOUNT = 300;
    private static final Integer SIMULATION_AMOUNT = 5;

    public static void main(String[] args) { //TODO deve ser passado o nome da config pra rodar. Ex: java run main Q1
        String configToRun = "test";
        if (args.length > 0) {
            configToRun = args[0];
        }
        System.out.println("Running config " + configToRun);
        YmlReader ymlReader = new YmlReader();
        RandomNumGenerator randomNumGenerator = new RandomNumGenerator();
        Simulation simulation = new Simulation();

        //Ler arquivo de configuracao
        Map<String, SimulationConfig> simulationConfigMap = ymlReader.readFile(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        if (Objects.isNull(simulationConfigMap)) {
            System.out.println("Config file possible wrong or cannot be read");
            return;
        }
        //gerar numeros aleatorios
        SimulationConfig simulationConfig = simulationConfigMap.get(configToRun);
        List<SimulationResult> averageResults = new ArrayList<>();

        for (int i = 0; i < SIMULATION_AMOUNT; i++) {
            List<Double> randomList = randomNumGenerator.generate(RANDOM_NUM_AMOUNT, simulationConfig);
            if (configToRun.equals("test")) {
                randomList = mockRandomList();
            } else if (configToRun.equals("test2")) {
                randomList = mockRandomList2();
            }
//        writeFile(randomList);
            //realizar simulacao
            simulation.config(simulationConfig, randomList);
            SimulationResult result = simulation.run(i, configToRun);
            averageResults.add(result);
        }
        System.out.println("Fim da execucao");
        writeResults(averageResults, configToRun);
    }

    private static void writeResults(List<SimulationResult> averageResults, String queueId) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream("./results-" + queueId + ".txt"));
            printWriter.println("Tempo de cada execucao:");
            for (int count = 0; count < averageResults.size(); count++) {
                printWriter.println("Execucao " + count);
                SimulationResult result = averageResults.get(count);
                printWriter.println("Tempo de execucao: " + result.getTimeElapsed());
                for (int i = 0; i < result.getTimeElapsedInPositionQueue().size(); i++) {
                    printWriter.println("Tempo na posicao " + i + ": " + result.getTimeElapsedInPositionQueue().get(i));
                }
                printWriter.println("-------------");
            }

            printWriter.println("Media de execucao: ");
            Double totalTimeElapsed = 0D;
            Map<Integer, Double> totalTimeElapsedInPosition = new HashMap<>();
            for (int i = 0; i <= averageResults.get(0).getTimeElapsedInPositionQueue().size() - 1; i++) {
                totalTimeElapsedInPosition.put(i, 0.0);
            }

            for (int count = 0; count < averageResults.size(); count++) {
                SimulationResult result = averageResults.get(count);
                totalTimeElapsed += result.getTimeElapsed();

                for (int i = 0; i < result.getTimeElapsedInPositionQueue().size(); i++) {
                    Double amount = totalTimeElapsedInPosition.get(i);
                    amount += result.getTimeElapsedInPositionQueue().get(i);
                    totalTimeElapsedInPosition.replace(i, amount);
                }
            }
            printWriter.println("Tempo medio de execucao: " + totalTimeElapsed / averageResults.size());
            for (int i = 0; i < totalTimeElapsedInPosition.size(); i++) {
                printWriter.println("Tempo na posicao " + i + ": " + totalTimeElapsedInPosition.get(i) / averageResults.size());
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Double> mockRandomList2() {
        List<Double> mock = new ArrayList<>();
        mock.add(0.8);
        mock.add(0.2);
        mock.add(0.1);
        mock.add(0.9);
        mock.add(0.3);
        mock.add(0.4);
        mock.add(0.7);
        return mock;
    }

    private static List<Double> mockRandomList() {
        List<Double> mock = new ArrayList<>();
        mock.add(0.3276);
        mock.add(0.8851);
        mock.add(0.1643);
        mock.add(0.5542);
        mock.add(0.6813);
        mock.add(0.7221);
        mock.add(0.9881);
        return mock;
    }

    private static void writeFile(List<Double> randomList) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream("./random-num.txt"));
            randomList.forEach(printWriter::println);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
