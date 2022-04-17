package YmlReader;

import com.sun.tools.javac.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class YmlReader {
    public YmlReader(){}

    public Map<String, SimulationConfig> readFile(){
        File file = getFileByGambi();

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

    private File getFileByGambi(){
        URL url = this.getClass().getResource("YmlReader.class");
        String[] paths = url.getPath().split("\\/");
        List<String> configPath = new ArrayList<>();
        int count = 0;
        for (int i = 0; !paths[i].equals("out"); i++) {
            configPath.add(paths[i]);
        }
        configPath.add("src");
        configPath.add("config.txt");
        System.out.println(url.getPath());

        return new File(String.join("/", configPath));
    }

}
