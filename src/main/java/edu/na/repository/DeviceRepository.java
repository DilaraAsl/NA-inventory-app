package edu.na.repository;


import edu.na.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    @Query(value = "select d from Device d where d.serialNumber=?1")
    Device findDeviceBySerialNo(String serialNo);

    @Query(value = "select * from devices where is_deleted=false and is_decommissioned=false", nativeQuery = true)
    List<Device> listUndeletedUnCommissionedDevices();

    @Query(value = "SELECT COUNT(*) FROM devices WHERE check_me_out = true and is_deleted=false and is_decommissioned=false;", nativeQuery = true)
    Integer retrieveTotalNoOfDevicesAvailableToCheckOut();

    @Query(value = "SELECT COUNT(*) FROM devices WHERE check_me_out = false and is_deleted=false and is_decommissioned=false;", nativeQuery = true)
    Integer retrieveTotalNoOfDevicesCheckedOut();

    @Query(value = "SELECT make, model, COUNT(*) FROM devices WHERE check_me_out = true and is_deleted=false and is_decommissioned=false GROUP BY make, model;", nativeQuery = true)
    List<Object[]> retrieveTotalNoOfDeviceModelAvailableCheckedOut();
    @Query(value = "SELECT category,COUNT(*) from devices where check_me_out = true and is_deleted=false and is_decommissioned=false GROUP BY category",nativeQuery = true)
    List<Object[]> retrieveTotalNoOfDeviceCategoryAvailableToCheckOut();

}
