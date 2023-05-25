package edu.na.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
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

}
