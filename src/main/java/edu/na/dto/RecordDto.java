package edu.na.dto;

import edu.na.entity.Device;
import edu.na.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor

public class RecordDto {

    private Long id;
    private TransactionDto transaction;

    private LocalDate date;

    private String notes;

    private UserDto user;

    private DeviceDto device;

}
