package edu.na.repository;


import edu.na.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeviceRepository extends JpaRepository<Device,Long> {
@Query(value = "select d from devices d where d.serialNo=?1",nativeQuery = true)
    Device findDeviceBySerialNo(String serialNo);
}
