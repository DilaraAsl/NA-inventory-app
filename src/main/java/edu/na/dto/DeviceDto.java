package edu.na.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {
    private Long id;
    @NotBlank(message = "First name is required.")
    @Size(max = 50, min = 2, message = "Device Make must be between 2 and 50 characters long")
    private String make;
    @NotBlank (message = "First name is required.")
    @Size(max = 50, min = 2, message = "Device Model must be between 2 and 50 characters long")
    private String model;
    @NotBlank (message = "First name is required.")
    @Size(max = 50, min = 2, message = "Serial Number must be between 2 and 50 characters long")
    private String serialNumber;

    @Digits(integer = 10, fraction = 0, message = "Price must be a number.")
    private Long price;

    @Digits(integer = 10, fraction = 0, message = "Quantity must be a number.")
    private int quantity;

    private boolean checkMeOut=true;
    public int getCheckMeOutInt(){
        return isCheckMeOut()? 1:0;
    }
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
