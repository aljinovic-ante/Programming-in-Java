package drugaVjezba;


import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {
    public static void main(String[] args) throws MqttException {
        Sensor sensor1=new Sensor(-3266.8 , 3266.8,10,"Celsius","Water temperature");
        Sensor sensor2=new Sensor(0 , 65.336,1000,"Bar","Water pressure");
        Sensor sensor3=new Sensor(0 , 65336,0,"Liter","Water consumption 1");
        Sensor sensor4=new Sensor(0 , 6533.6,10,"Cubic metre","Water consumption 2");
       /* System.out.println(sensor1.getSensorData());
        System.out.println(sensor2.getSensorData());*/

        Device device=new Device("drugaVjezba","tcp://localhost:1883");
        device.addSensor(sensor1);
        device.addSensor(sensor2);
        device.addSensor(sensor3);
        device.addSensor(sensor4);
        // device.addSensor(sensor3);
        device.startDevice();

    }
}
