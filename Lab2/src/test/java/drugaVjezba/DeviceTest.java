package drugaVjezba;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {
    private Device device;

    @BeforeEach
    public void setUp() throws MqttException {
        device=new Device("TEST -------------","tcp://localhost:1883");
        Sensor sensor1=new Sensor(-3266.8 , 3266.8,10,"Celsius","Water temperature");
        Sensor sensor2=new Sensor(0 , 65.336,1000,"Bar","Water pressure");
        device.addSensor(sensor1);
        device.addSensor(sensor2);
    }

    @Test
    public  void testAddSensor()
    {
        assertEquals(2,device.getSensors().size());
    }

    @Test
    public void testStartDevice()
    {
        try{device.startDevice();}
        catch (MqttException e){
            Assertions.fail("error");
        }
    }

}