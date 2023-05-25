package edu.na.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor

@Entity
@Table (name="devices")
//@Where(clause = "is_deleted=false")
//@Where(clause = "is_deleted=false and is_commissioned=false")
public class Device extends BaseEntity{

    String make;
    String model;
    String serialNumber;
    Long price;

    int quantity;
    boolean checkMeOut;// by default it is set to false
    boolean isDecommissioned=false;

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

    public void setCheckMeOut(boolean checkMeOut) {
        this.checkMeOut = checkMeOut;
    }

    public boolean isDecommissioned() {
        return isDecommissioned;
    }

    public void setDecommissioned(boolean decommissioned) {
        isDecommissioned = decommissioned;
    }
}
