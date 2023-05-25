package edu.na.service.impl;

import edu.na.dto.DeviceDto;
import edu.na.dto.RecordDto;
import edu.na.dto.UserDto;
import edu.na.entity.Record;
import edu.na.exceptions.RecordNotFoundException;
import edu.na.repository.RecordRepository;
import edu.na.service.DeviceService;
import edu.na.service.RecordService;
import edu.na.service.SecurityService;
import edu.na.util.MapperUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {
    private final SecurityService securityService;
    private final RecordRepository recordRepository;
    private final DeviceService deviceService;
    private final MapperUtil mapperUtil;

    public RecordServiceImpl(@Lazy SecurityService securityService, RecordRepository recordRepository, @Lazy DeviceService deviceService, MapperUtil mapperUtil) {
        this.securityService = securityService;
        this.recordRepository = recordRepository;
        this.deviceService = deviceService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<RecordDto> findAll() {
        return recordRepository.findAll().stream()
                .sorted(Comparator.comparing(Record::getDate).reversed())
                .map(record -> mapperUtil.convert(record, new RecordDto()))
                .collect(Collectors.toList());


    }

    @Override
    public List<RecordDto> findAllRecordsThatAreOpenTransactions() {
        return recordRepository.retrieveOpenTransactions().stream()
                .sorted(Comparator.comparing(Record::getDate).reversed())
                .map(record -> mapperUtil.convert(record, new RecordDto()))
                .collect(Collectors.toList());


    }

    // saveRecord should be used in update and delete methods to eliminate the boiler plate code
    @Override
    public RecordDto save(RecordDto recordDto) {
        DeviceDto deviceDto = deviceService.findDevice(recordDto.getDevice().getId());
        if (recordDto.getTransaction().getId() == 1 && deviceDto.isCheckMeOut()) {
            deviceDto.setCheckMeOut(false);
            recordDto.setTransactionComplete(false);
        } else if (recordDto.getTransaction().getId() == 2 && !deviceDto.isCheckMeOut()) {
            deviceDto.setCheckMeOut(true);
            //if transaction is complete , if it is true, the record can be deleted
            // if transaction is complete then the assignment record should show that the transaction is complete
            BigInteger userId = BigInteger.valueOf(recordDto.getUser().getId());
            BigInteger deviceId = BigInteger.valueOf(recordDto.getDevice().getId());
            System.out.println("User Id: " + userId);
            System.out.println("Device Id: " + deviceId);
            Record assignmentRecord = recordRepository.findAssignedDeviceRecordByUserIdAndDeviceId(userId, deviceId);
            assignmentRecord.setTransactionComplete(true);
            recordRepository.save(assignmentRecord);
            recordDto.setTransactionComplete(true);
        }
        deviceService.save(deviceDto);
        recordDto.setUpdatedBy(securityService.getLoggedInUser().getUser_name());
        recordRepository.save(mapperUtil.convert(recordDto, new Record()));
        return recordDto;
    }

    @Override
    public RecordDto update(RecordDto recordDto) {
        recordDto.setUpdatedBy(securityService.getLoggedInUser().getUser_name());
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

        Record record = recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id:" + id + " Not Found"));
        // if the record shows the device is retrieved than the record can be deleted
        if (record.isTransactionComplete()) {
            record.setIsDeleted(true);
            record.setUpdatedBy(securityService.getLoggedInUser().getUser_name());
            recordRepository.save(record);
        }
        RecordDto recordDto = mapperUtil.convert(record, new RecordDto());
        recordDto.setUpdatedBy(record.getUpdatedBy());
        return recordDto;
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
    public List<RecordDto> listOpenRecordsOfUser(UserDto userDto) {
        return recordRepository.retrieveIncompleteTransactionUserRecords(userDto.getUser_name()).stream()
                .sorted(Comparator.comparing(Record::getDate).reversed())
                .map(record -> mapperUtil.convert(record, new RecordDto()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deviceExistInRecord(Long id) {
        return recordRepository.deviceExistsInRecord(id);

    }

    @Override
    public Boolean isTransactionCompleteByUser(Long userId) {
        // postgresql does not accept long type, but BigInteger
        BigInteger userIdBigInteger = BigInteger.valueOf(userId);
        return recordRepository.isTransactionCompleteByUserId(userIdBigInteger);

    }

}
