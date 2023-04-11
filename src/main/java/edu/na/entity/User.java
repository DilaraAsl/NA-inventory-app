package edu.na.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
@Where(clause = "is_deleted=false")
@Validated

public class User extends BaseEntity {
    @Size(min = 2, message = "Name should have at least two characters")
    @Column(name = "f_name")
    private String first_name;
    @Size(min = 2, message = "Name should have at least two characters")
    @Column(name = "l_name")
    private String last_name;
    @Column(unique = true, name = "user_name")
    private String user_name;
    private String email;
    private String password;
    private String officeNo;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
