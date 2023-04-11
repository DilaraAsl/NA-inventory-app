package edu.na.service;

import edu.na.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {
    UserDto getLoggedInUser();
}
