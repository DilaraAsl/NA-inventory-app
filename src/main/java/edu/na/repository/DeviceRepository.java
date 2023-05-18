package edu.na.repository;


import edu.na.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeviceRepository extends JpaRepository<Device,Long> {
@Query(value = "select d from Device d where d.serialNumber=?1")
    Device findDeviceBySerialNo(String serialNo);
}
