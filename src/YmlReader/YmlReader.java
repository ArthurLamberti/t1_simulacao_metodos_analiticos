package YmlReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class YmlReader {
    public YmlReader(){}

    public Map<String, SimulationConfig> readFile(){
        File file = new File("src/config.txt");
        DecodeFile decodeFile = new DecodeFile();
        Map<String, SimulationConfig> simulationMap = null;
        try {
            String fileContent = Files.readString(file.toPath());
//            System.out.println(fileContent);
            simulationMap = decodeFile.decode(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return simulationMap;
    }

}
