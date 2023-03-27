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
@JsonIgnore // exclude this field from Json output
    Long id;
    String make;
    String model;
    Long serialNumber;
    Long price;

    int quantity;

    boolean checkMeOut;

}
