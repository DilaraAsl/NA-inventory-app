package edu.na.converter;

import edu.na.dto.UserDto;

import edu.na.service.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements Converter <String,UserDto>{
    private final UserService userService;

    public UserDtoConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDto convert(String source) {
        if(source.equals(null) ||source.isBlank())
        return null;
        return userService.findById(Long.parseLong(source));
    }
}
