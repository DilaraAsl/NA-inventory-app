package edu.na.service;

import edu.na.dto.DeviceDto;
import edu.na.dto.RecordDto;
import edu.na.dto.UserDto;

import java.util.List;

public interface RecordService {
    public List<RecordDto> findAll();
    public RecordDto save(RecordDto recordDto);
    public RecordDto update(RecordDto recordDto);

    public RecordDto findById(Long id);
    public RecordDto delete(Long id);
    public List<RecordDto> listAllRecordsOfDevice(String serialNo);

    public List<RecordDto> listAllRecordsOfUser(UserDto userDto);
}
