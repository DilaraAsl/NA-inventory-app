package edu.na.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.hibernate.type.TrueFalseType;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
@Where(clause = "is_deleted=false")
@Validated

public class User extends BaseEntity {
//    @Size(min = 2, message = "Name should have at least two characters")
    @Column(name = "f_name")
    private String first_name;
//    @Size(min = 2, message = "Name should have at least two characters")
    @Column(name = "l_name")
    private String last_name;
    @Column(unique = true, name = "user_name")
    private String user_name;
//    @Email (message = "Please provide a valid email address")
    @Column(unique = true)
    private String email;

//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
//            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one digit")
    private String password;
    private String officeNo;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
