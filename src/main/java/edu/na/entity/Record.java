package edu.na.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "records")
@Where(clause = "is_deleted=false")

public class Record extends BaseEntity {


    @OneToOne
    private Transaction transaction;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "notes", length = 50)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Device device;

    private String updatedBy;
//    boolean isTransactionComplete=false;

    // Constructor, getters, and setters
}