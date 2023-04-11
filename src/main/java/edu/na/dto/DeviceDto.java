package edu.na.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    private boolean checkMeOut=true;
    public void setId(Long id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCheckMeOut(boolean checkMeOut) {
        this.checkMeOut = checkMeOut;
    }




    public DeviceDto(String make, String model, String serialNumber) {
        this.make = make;
        this.model = model;
        this.serialNumber = serialNumber;
    }
}
