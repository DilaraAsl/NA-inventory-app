package edu.na.service;

import edu.na.dto.RecordDto;
import edu.na.dto.RoleDto;
import edu.na.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto save(UserDto userDto);
    UserDto findById(Long id);
    UserDto delete(Long id);
    UserDto findByUserName(String userName);
    boolean isUserNameUnique(String userName);
    String findRoleByUserName(String userName);
    RoleDto findRoleIdByUserName(String userName);
    List<UserDto> userListOrderedByUserName();

}
