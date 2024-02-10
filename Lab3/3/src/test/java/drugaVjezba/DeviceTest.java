package drugaVjezba;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeviceTest {
    @Test
    public void deviceTest() {
        Device Sensor =new Device("src/main/resources/package.json");
        try {
            Sensor=Sensor.cfgFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(Sensor);
        assertNotNull(Sensor.getSensors());
        assertNotNull(Sensor.getBroker());
        assertNotNull(Sensor.getTopic());
    }
}