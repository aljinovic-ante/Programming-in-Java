package hr.antealjinovic.service;

import hr.antealjinovic.models.Device;
import hr.antealjinovic.models.Record;
import hr.antealjinovic.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@AllArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    public Long totalConsumptionByYear(int year) {
        return recordRepository.findTotalConsumptionByYear(year)
                .orElseThrow(() -> new NoSuchElementException("No records for that year"));
    }

    public List<Record> allMonthsConsumptionByYear(int year) {
        List<Record> records = recordRepository.findAllMonthsConsumptionByYear(year);

        if (records.isEmpty()) {
            throw new NoSuchElementException("No records for that year");
        }

        return records;
    }

    public List<Record> monthConsumptionByYear(int year,int month) {
        List<Record> records = recordRepository.findMonthConsumptionByYear(year,month);

        if (records.isEmpty()) {
            throw new NoSuchElementException("No records for that month of year");
        }

        return records;

    }

    public Record addRecord(Long deviceId) {
        Device device = recordRepository.findDeviceById(deviceId)
                .orElseThrow(() -> new NoSuchElementException("Device not found with id: " + deviceId));

        Long electricityConsumption = (long) (Math.random() * 500);
        int year = 2022 + (int) (Math.random() * 3);
        int month;

        do {
            month = (int) (Math.random() * 12) + 1;
        } while (recordRepository.existsByDeviceAndMonthAndYear(device, month, year));


        Record record = new Record();
        record.setConsumption(electricityConsumption);
        record.setMonth(month);
        record.setYear(year);
        record.setDevice(device);

        return recordRepository.save(record);
    }

    public void deleteRecord(Long recordId) {
        Record record = recordRepository.findRecordById(recordId)
                .orElseThrow(() -> new NoSuchElementException("Record not found with id: " + recordId));

        recordRepository.delete(record);
    }


    public void updateRecord(Long recordId) {
        Record record = recordRepository.findRecordById(recordId)
                .orElseThrow(() -> new NoSuchElementException("Record not found with id: " + recordId));

        Long electricityConsumption = (long) (Math.random() * 500);
        record.setConsumption(electricityConsumption);

        recordRepository.save(record);
    }




}