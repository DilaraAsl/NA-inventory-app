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
    private Long id;
    private String make;
    private String model;
    private String serialNumber;
    private Long price;

    private int quantity;

    private boolean checkMeOut;


    public DeviceDto(String make, String model, String serialNumber) {
        this.make = make;
        this.model = model;
        this.serialNumber = serialNumber;
    }
}
