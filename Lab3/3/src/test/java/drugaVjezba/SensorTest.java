package drugaVjezba;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorTest {

    @Test
    void sensorTest() {

        Sensor sensor1=new Sensor(-3266.8 , 3266.8,10,"Celsius","Water temperature");
        double startRange = -3266.8;
        double endRange = 3266.8;
        Assert.assertTrue(sensor1.getValue() >= startRange && sensor1.getValue() <= endRange);
        System.out.println("1. test => "+ sensor1.getValue());

        Sensor sensor2=new Sensor(0 , 65.336,1000,"Bar","Water pressure");
        startRange = 0;
        endRange = 65.336;
        Assert.assertTrue(sensor2.getValue() >= startRange && sensor2.getValue() <= endRange);
        System.out.println("2. test => "+ sensor2.getValue());

        Sensor sensor3=new Sensor(0 , 65336,0,"Liter","Water consumption 1");
        startRange = 0;
        endRange = 65336;
        Assert.assertTrue(sensor3.getValue() >= startRange && sensor3.getValue() <= endRange);
        System.out.println("3. test => "+ sensor3.getValue());

        Sensor sensor4=new Sensor(0 , 6533.6,10,"Cubic metre","Water consumption 2");
        startRange = 0;
        endRange = 6533.6;
        Assert.assertTrue(sensor4.getValue() >= startRange && sensor4.getValue() <= endRange);
        System.out.println("4. test => "+ sensor4.getValue());

    }
}

