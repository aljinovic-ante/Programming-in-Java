package hr.antealjinovic.controller;

import hr.antealjinovic.models.Device;
import hr.antealjinovic.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("devices")
@AllArgsConstructor
public class DeviceController {

    public final DeviceService deviceService;

    @GetMapping
    public Device getDeviceByName(@RequestParam String name) {
        return deviceService.findByName(name);
    }

    @GetMapping("/{deviceId}")
    public Device getDeviceWithRecords(@PathVariable Long deviceId) {
        return deviceService.findByIdWithRecords(deviceId);
    }

    @PostMapping
    public ResponseEntity<?> createDevice() {
        try {
            Device savedDevice = deviceService.saveDevice();
            return ResponseEntity.ok(savedDevice);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}
