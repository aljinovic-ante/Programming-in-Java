package hr.antealjinovic.repository;

import hr.antealjinovic.models.Record;
import hr.antealjinovic.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query("SELECT d FROM Device d WHERE d.id = :deviceId")
    Optional<Device> findDeviceById(@Param("deviceId") Long deviceId);
    Optional<Record> findRecordById(@Param("recordId") Long deviceId);
    boolean existsByDeviceAndMonthAndYear(Device device, int month, int year);

    @Query("SELECT SUM(r.consumption) FROM Record r WHERE r.year = :year")
    Optional<Long> findTotalConsumptionByYear(int year);
    @Query("SELECT r FROM Record r WHERE r.year = :year")
    List<Record> findAllMonthsConsumptionByYear(int year);

    @Query("SELECT r FROM Record r WHERE r.year = :year AND r.month = :month")
    List<Record> findMonthConsumptionByYear(int year, int month);




}