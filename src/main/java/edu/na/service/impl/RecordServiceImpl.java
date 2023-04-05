package edu.na.service.impl;

import edu.na.dto.DeviceDto;
import edu.na.dto.RecordDto;
import edu.na.dto.UserDto;
import edu.na.entity.Device;
import edu.na.entity.Record;
import edu.na.repository.RecordRepository;
import edu.na.service.RecordService;
import edu.na.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RecordServiceImpl implements RecordService {
private final RecordRepository recordRepository;
private final MapperUtil mapperUtil;

    public RecordServiceImpl(RecordRepository recordRepository, MapperUtil mapperUtil) {
        this.recordRepository = recordRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<RecordDto> findAll() {
       return  recordRepository.findAll().stream()
                .map(record -> mapperUtil.convert(record,new RecordDto()))
                .collect(Collectors.toList());

    }

    @Override
    public RecordDto save(RecordDto recordDto) {
       recordRepository.save(mapperUtil.convert(recordDto,new Record()));
        return recordDto;
    }

    @Override
    public RecordDto update(RecordDto recordDto) {
        Optional<Record> record=recordRepository.findById(recordDto.getId());
        Record newRecord=mapperUtil.convert(recordDto,new Record());
        newRecord.setId(record.get().getId());
        recordRepository.save(newRecord);
        return recordDto;
    }

    @Override
    public RecordDto findById(Long id) {
      return mapperUtil.convert(recordRepository.findById(id),new RecordDto());

    }

    @Override
    public RecordDto delete(Long id) {
        return null;
    }

    @Override
    public List<RecordDto> listAllRecordsOfDevice(String serialNo) {
        List<Record> records=recordRepository.retrieveDeviceRecords(serialNo);
        return records.stream().map(record -> mapperUtil.convert(record,new RecordDto())).collect(Collectors.toList());
    }

    @Override
    public List<RecordDto> listAllRecordsOfUser(UserDto userDto) {

        return recordRepository.retrieveUserRecords(userDto.getUser_name()).stream()
                .map(record->mapperUtil.convert(record,new RecordDto()))
                .collect(Collectors.toList());
    }
}
