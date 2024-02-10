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

    @DeleteMapping("/delete/{recordId}")
    public void deleteRecord(@PathVariable Long recordId) {
        recordService.deleteRecord(recordId);
    }

    @PutMapping("/update/{recordId}")
    public void updateRecord(@PathVariable Long recordId) {
        recordService.updateRecord(recordId);
    }

    @GetMapping("/total/{year}")
    public Long totalConsumptionByYear(@PathVariable int year) {
        return recordService.totalConsumptionByYear(year);
    }

    @GetMapping("/all/{year}")
    public List<Record> allMonthsConsumptionByYear(@PathVariable int year) {
        return recordService.allMonthsConsumptionByYear(year);
    }

    @GetMapping("/total/{year}/{month}")
    public List<Record> monthConsumptionByYear(@PathVariable int year,@PathVariable int month) {
        return recordService.monthConsumptionByYear(year,month);
    }



}
