package edu.na.service;

import edu.na.dto.RecordDto;
import edu.na.dto.UserDto;

import java.util.List;

public interface RecordService {
    List<RecordDto> findAll();
    RecordDto save(RecordDto recordDto);
    RecordDto update(RecordDto recordDto);

    RecordDto findById(Long id);
    RecordDto delete(Long id);
    List<RecordDto> listAllRecordsOfDevice(String serialNo);

    List<RecordDto> listAllRecordsOfUser(UserDto userDto);


    boolean deviceExistInRecord(Long id);

}
