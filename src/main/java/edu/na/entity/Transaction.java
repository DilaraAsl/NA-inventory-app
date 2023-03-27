package edu.na.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
// add condition

public class Transaction extends BaseEntity {
   private  String description;

}
