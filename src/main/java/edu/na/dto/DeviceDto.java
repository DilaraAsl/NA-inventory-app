package edu.na.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;



@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {
    private Long id;
    @NotBlank(message = " Make is required.")
    @Size(max = 50, min = 2, message = "Device Make must be between 2 and 50 characters long")
    private String make;
    @NotBlank (message = "Model is required.")
    @Size(max = 50, min = 2, message = "Device Model must be between 2 and 50 characters long")
    private String model;
    @NotBlank (message = "Serial Number name is required.")
    @Size(max = 50, min = 2, message = "Serial Number must be between 2 and 50 characters long")
    private String serialNumber;

    @Digits(integer = 10, fraction = 0, message = "Price must be a number.")
    @Positive(message = "Price must be greater than zero.")
    private Long price;

    @Digits(integer = 10, fraction = 0, message = "Quantity must be a number.")
    @Positive(message = "Quantity must be greater than zero.")
    private int quantity;
    private boolean checkMeOut=true;
    private boolean isDecommissioned;
    private Boolean isDeleted;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCheckMeOutInt(){
        return isCheckMeOut()? 1:0;
    }

    public void setCheckMeOut(boolean checkMeOut) {
        this.checkMeOut = checkMeOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isCheckMeOut() {
        return checkMeOut;
    }

    public boolean isDecommissioned() {
        return isDecommissioned;
    }

    public void setDecommissioned(boolean decommissioned) {
        isDecommissioned = decommissioned;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public DeviceDto(String make, String model, String serialNumber) {
        this.make = make;
        this.model = model;
        this.serialNumber = serialNumber;
    }
}
