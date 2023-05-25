package edu.na.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    Long id;
    @NotBlank (message = "First name is required.")
    @Size(max = 50, min = 2, message = "First name must be between 2 and 50 characters long")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only letters.No spaces allowed.")
    private String first_name;
    @NotBlank (message = "Last name is required.")
    @Size(max = 50, min = 2, message = "Last name must be between 2 and 50 characters long")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only letters. No spaces allowed")
    private String last_name;
    @NotBlank(message = "Username is required.")
    @Email(message = "Email is required" )
    private String user_name;
//    @NotBlank(message = "Email is required")
//    @Email(message = "Email is required field" )
    private String email;

    // password is assigned only to admin and student worker
    private String password;

//    public UserDto(Long id, String user_name) {
//        this.id = id;
//        this.user_name = user_name;
//    }

//    @NotBlank(message = "Office number is required")
    private String officeNo;
    @NotNull(message="Role is required")
    private RoleDto roleDto;
}
