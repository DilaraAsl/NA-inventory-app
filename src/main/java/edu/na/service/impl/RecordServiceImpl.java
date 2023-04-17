package edu.na.service.impl;

import edu.na.dto.DeviceDto;
import edu.na.dto.RecordDto;
import edu.na.dto.UserDto;
import edu.na.entity.Record;
import edu.na.exceptions.RecordNotFoundException;
import edu.na.repository.RecordRepository;
import edu.na.service.DeviceService;
import edu.na.service.RecordService;
import edu.na.util.MapperUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;
    private final DeviceService deviceService;
    private final MapperUtil mapperUtil;

    public RecordServiceImpl(RecordRepository recordRepository, @Lazy DeviceService deviceService, MapperUtil mapperUtil) {
        this.recordRepository = recordRepository;
        this.deviceService = deviceService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<RecordDto> findAll() {
        return recordRepository.findAll().stream()
                .map(record -> mapperUtil.convert(record, new RecordDto()))
                .collect(Collectors.toList());

    }

    @Override
    public RecordDto save(RecordDto recordDto) {
        DeviceDto deviceDto = deviceService.findDevice(recordDto.getDevice().getId());
        if (recordDto.getTransaction().getId() == 1 && deviceDto.isCheckMeOut())
            deviceDto.setCheckMeOut(false);
        else if (recordDto.getTransaction().getId() == 2 && !deviceDto.isCheckMeOut())
            deviceDto.setCheckMeOut(true);
        deviceService.save(deviceDto);
        recordRepository.save(mapperUtil.convert(recordDto, new Record()));
        return recordDto;
    }

    @Override
    public RecordDto update(RecordDto recordDto) {
        Optional<Record> record = recordRepository.findById(recordDto.getId());
        Record newRecord = mapperUtil.convert(recordDto, new Record());
        newRecord.setId(record.get().getId());
        recordRepository.save(newRecord);
        return recordDto;
    }

    @Override
    public RecordDto findById(Long id) {
        return mapperUtil.convert(recordRepository.findById(id), new RecordDto());

    }

    @Override
    public RecordDto delete(Long id) {
        Record record=recordRepository.findById(id).orElseThrow(()->new RecordNotFoundException("Record with id:"+id+" Not Found"));
        record.setIsDeleted(true);
        recordRepository.save(record);
        return mapperUtil.convert(record,new RecordDto());
    }

    @Override
    public List<RecordDto> listAllRecordsOfDevice(String serialNo) {
        List<Record> records = recordRepository.retrieveDeviceRecords(serialNo);
        return records.stream()
                .sorted(Comparator.comparing(Record::getDate).reversed())
                .map(record -> mapperUtil.convert(record, new RecordDto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RecordDto> listAllRecordsOfUser(UserDto userDto) {

        return recordRepository.retrieveUserRecords(userDto.getUser_name()).stream()
                .sorted(Comparator.comparing(Record::getDate).reversed())
                .map(record -> mapperUtil.convert(record, new RecordDto()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deviceExistInRecord(Long id) {
        return recordRepository.deviceExistsInRecord(id);

    }
}
