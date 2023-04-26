package edu.na.dto;

import edu.na.entity.Device;
import edu.na.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.Format;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor

public class RecordDto {

    private Long id;
    private TransactionDto transaction;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    private String notes;

    private UserDto user;

    private DeviceDto device;

    private String updatedBy;
    private boolean isTransactionComplete=false;

}
