package edu.na.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    Long id;
    @NotBlank (message = "First name is required field")
    @Size(max = 50, min = 2, message = "First name must be between 2 and 50 characters long")
    private String first_name;
    @NotBlank (message = "Last name is required field")
    @Size(max = 50, min = 2, message = "First name must be between 2 and 50 characters long")
    private String last_name;
    @NotBlank(message = "Username is required")
    @Email(message = "Email is required field" )
    private String user_name;
    @NotBlank(message = "Email is required")
    @Email(message = "Email is required field" )
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String officeNo;
    @NotBlank
    private RoleDto roleDto;
}
