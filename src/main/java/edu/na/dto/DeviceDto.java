package edu.na.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {
    Long id;
    String make;
    String model;
    String serialNumber;
    Long price;

    int quantity;

    boolean checkMeOut;

    public DeviceDto(String make, String model, String serialNumber) {
        this.make = make;
        this.model = model;
        this.serialNumber = serialNumber;
    }
}
