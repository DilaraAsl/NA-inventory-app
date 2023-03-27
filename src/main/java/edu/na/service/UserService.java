package edu.na.service;

import edu.na.dto.RecordDto;
import edu.na.dto.UserDto;

import java.util.List;

public interface UserService {
    public List<UserDto> findAll();
    public UserDto save(UserDto userDto);

    public UserDto findById(Long id);
    public UserDto delete(Long id);
    public UserDto findByUserName(String userName);
}
