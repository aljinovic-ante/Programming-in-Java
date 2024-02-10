package hr.antealjinovic.service;

import hr.antealjinovic.exception.DeviceNotFoundException;
import hr.antealjinovic.exception.SameRecordExistsException;
import hr.antealjinovic.models.Device;
import hr.antealjinovic.models.Record;
import hr.antealjinovic.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.data.domain.Page;

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

    public Record addRecord2(Long deviceID, int year, int month, Long consumption) {
        Optional<Record> existingRecord = recordRepository.findRecordByDeviceIdAndYearAndMonth(deviceID, year, month);
        if (existingRecord.isPresent()) {
            throw new SameRecordExistsException("A record already exists for device:" + deviceID + ", year:" + year + " and month:" + month);
        }

        Device device = recordRepository.findDeviceById(deviceID)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found with id: " + deviceID));

        Record record = new Record();
        record.setConsumption(consumption);
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

    public Page findPaginated(PageRequest pageable) {
        List<Record> allRecords = recordRepository.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Record> list;

        if (allRecords.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allRecords.size());
            list = allRecords.subList(startItem, toIndex);
        }

        Page<Record> recordPage = new PageImpl<Record>(list, PageRequest.of(currentPage, pageSize), allRecords.size());

        return recordPage;
    }
    public List<Device> devicesByRecord() {
        List<Record> allRecords = recordRepository.findAll();
        List<Device> devices = new ArrayList<>();
        allRecords.forEach(record -> {
            long deviceId = record.getDevice().getId();
            devices.add(recordRepository.findById(deviceId).orElse(null).getDevice());
        });

        return devices;
    }



}