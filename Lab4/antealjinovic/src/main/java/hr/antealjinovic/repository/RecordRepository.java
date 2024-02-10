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


}