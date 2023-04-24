package edu.na.service;


import edu.na.dto.DeviceDto;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    List<DeviceDto> findAll();
    DeviceDto save(DeviceDto deviceDto);

    DeviceDto findDevice(Long id);
    DeviceDto delete(Long id);

    DeviceDto update(DeviceDto deviceDto);

    DeviceDto findDeviceBySerialNo(String serialNo);
    List<DeviceDto> findDevicesToCheckOut();
    List<DeviceDto> findDevicesToCheckIn();

    List<DeviceDto> findDevicesByUserId(Long userId);
    Map<String,Map<String,Integer>> mapDevicesByMakeModelAndCount();
}
