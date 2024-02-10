package drugaVjezba;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Device Sensor =new Device("C:/Users/antea/IdeaProjects/novi/src/main/resources/package.json");
        try {
            Sensor=Sensor.cfgFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sensor.runDevice();
    }
}