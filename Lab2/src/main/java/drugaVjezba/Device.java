package drugaVjezba;

import lombok.Getter;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedSet;

@Getter
public class Device {
    private String topic;
    private String address;
    private List<Sensor> sensors;
    private MqttClient myClient;

    public Device(String topic,String address) throws MqttException {
        this.topic=topic;
        this.address=address;
        sensors=new ArrayList<>();
        myClient=new MqttClient(address,topic);
    }

    public void addSensor(Sensor sensor){
        sensors.add(sensor);
    }

    public void startDevice() throws MqttException {
        MqttConnectOptions connection = new MqttConnectOptions();
        myClient.connect(connection);
        int i=1;
        for(Sensor sensor :sensors){
            String message=i+". podatak:\n";
            MqttMessage messageBytes = new MqttMessage(message.getBytes());
            myClient.publish("-", messageBytes);
            i++;
            message=sensor.getSensorData();
            messageBytes = new MqttMessage(message.getBytes());
            myClient.publish(topic, messageBytes);

        }
        myClient.disconnect();
    }
}

