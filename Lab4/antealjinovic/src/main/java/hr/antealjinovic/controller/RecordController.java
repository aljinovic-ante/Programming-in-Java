package hr.antealjinovic.controller;

import hr.antealjinovic.models.Record;
import hr.antealjinovic.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("records")
@AllArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }


    @PostMapping("/add/{deviceId}")
    public Record addRecord(@PathVariable Long deviceId) {
        return recordService.addRecord(deviceId);
    }
}
