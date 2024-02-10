package drugaVjezba;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MqttTest {
    @Test
    public void mqttTest(){
        Device Sensor = new Device("src/main/resources/package.json");
        try{
            Sensor=Sensor.cfgFile();
            MqttClient client = new MqttClient(Sensor.getBroker(),Sensor.getTopic());
            client.connect();
            assertTrue(client.isConnected());
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
