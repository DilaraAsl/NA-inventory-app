package edu.na.service.impl;


import edu.na.dto.DeviceDto;
import edu.na.dto.RecordDto;
import edu.na.dto.UserDto;
import edu.na.entity.Device;
import edu.na.exceptions.DeviceNotFoundException;
import edu.na.repository.DeviceRepository;
import edu.na.service.DeviceService;
import edu.na.service.RecordService;
import edu.na.service.UserService;
import edu.na.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final RecordService recordService;
    private final UserService userService;
    private final MapperUtil mapperUtil;

    public DeviceServiceImpl(DeviceRepository deviceRepository, RecordService recordService, UserService userService, MapperUtil mapperUtil) {
        this.deviceRepository = deviceRepository;
        this.recordService = recordService;
        this.userService = userService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<DeviceDto> findAll() {
        return deviceRepository.findAll().stream()
                .sorted(Comparator.comparing(Device::getMake))
                .map(device -> mapperUtil.convert(device, new DeviceDto()))
                .collect(Collectors.toList());

    }
//    public List<DeviceDto> listBySerialNo() {
//        return findAll()
//                .stream()
//                .sorted(Comparator.comparingLong(deviceDto -> Long.parseLong(deviceDto.getSerialNumber())))
//                .collect(Collectors.toList());
//    }
public List<DeviceDto> listBySerialNo() {
    return findAll()
            .stream()
            .sorted(Comparator.comparing(DeviceDto::getSerialNumber))
            .collect(Collectors.toList());
}

    @Override
    public DeviceDto save(DeviceDto deviceDto) {
        deviceRepository.save(mapperUtil.convert(deviceDto, new Device()));
        return deviceDto;
    }

    @Override
    public DeviceDto findDevice(Long id) {
        return deviceRepository.findById(id).map(device -> mapperUtil.convert(device, new DeviceDto())).orElseThrow();

    }

    @Override
    public DeviceDto delete(Long id) {

        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found"));
        // if the id exists return the dto back without deleting
        if (!recordService.deviceExistInRecord(id)) {
            device.setIsDeleted(true);
            device.setCheckMeOut(false);
            deviceRepository.save(device);
        }
        return findDevice(id);
    }

    @Override
    public DeviceDto update(DeviceDto deviceDto) {
        Optional<Device> device = deviceRepository.findById(deviceDto.getId());
        Device updatedDevice = mapperUtil.convert(deviceDto, new Device());
        updatedDevice.setCheckMeOut(device.get().isCheckMeOut());
        updatedDevice.setIsDeleted(device.get().getIsDeleted());
        deviceRepository.save(updatedDevice);
        return deviceDto;
    }

    @Override
    public DeviceDto findDeviceBySerialNo(String serialNo) {
        Device device = deviceRepository.findDeviceBySerialNo(serialNo);
        return mapperUtil.convert(device, new DeviceDto());
    }

    @Override
    public List<DeviceDto> findDevicesToCheckOut() {
        return findAll().stream().filter(deviceDto -> !deviceDto.isCheckMeOut()).collect(Collectors.toList());
    }

    @Override
    public List<DeviceDto> findDevicesToCheckIn() {
        return findAll().stream().filter(deviceDto -> deviceDto.isCheckMeOut()).collect(Collectors.toList());
    }

    @Override
    public List<DeviceDto> findDevicesByUserId(Long userId) {
        UserDto userDto=userService.findById(userId);
        return recordService.listAllRecordsOfUser(userDto).stream()
                .map(RecordDto::getDevice)
                .filter(deviceDto -> !deviceDto.isCheckMeOut())
                .collect(Collectors.toList());

    }

    @Override
    public  Map<String,Map <String, Integer>> mapDevicesByMakeModelAndCount() {

        // Group the devices by make and model
        Map<String, Map<String, Integer>> data = findAll().stream()
                .collect(Collectors.groupingBy(DeviceDto::getMake, Collectors.groupingBy(DeviceDto::getModel,
                        Collectors.reducing(0, deviceDto -> deviceDto.isCheckMeOut() ? 1 : 0, Integer::sum))));

        return data;
    }
    @Override
    public  Map<String,Map <String, Integer>> mapAssignedDevicesByMakeModelAndCount() {

        // Group the devices by make and model
        Map<String, Map<String, Integer>> data = findAll().stream()
                .collect(Collectors.groupingBy(DeviceDto::getMake, Collectors.groupingBy(DeviceDto::getModel,
                        Collectors.reducing(0, deviceDto -> deviceDto.isCheckMeOut() ? 0 : 1, Integer::sum))));

        return data;
    }
}
