package edu.na.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    Long id;
    private String first_name;
    private String last_name;
    private String user_name;
    private String email;
    private String password;
    private String officeNo;
    private boolean isDeleted;
}
