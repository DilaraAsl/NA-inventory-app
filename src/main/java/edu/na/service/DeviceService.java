package edu.na.service;


import edu.na.dto.DeviceDto;

import java.util.List;

public interface DeviceService {
    public List<DeviceDto> findAll();
    public DeviceDto save(DeviceDto deviceDto);

    public DeviceDto findDevice(Long id);
    public DeviceDto delete(Long id);

    public DeviceDto update(DeviceDto deviceDto);

    public DeviceDto findDeviceBySerialNo(String serialNo);
}
