package edu.na.repository;


import edu.na.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeviceRepository extends JpaRepository<Device,Long> {
//    @Query(value = "select id from devices order by desc limit 1",nativeQuery = true)
//    Long getLatestRecordedDeviceId();
}
