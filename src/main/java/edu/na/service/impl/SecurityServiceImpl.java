package edu.na.service.impl;

import edu.na.dto.UserDto;
import edu.na.entity.User;
import edu.na.entity.UserPrincipal;
import edu.na.repository.UserRepository;
import edu.na.service.SecurityService;
import edu.na.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final UserService userService;
    private final UserRepository userRepository;

    public SecurityServiceImpl(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getLoggedInUser() {
        var currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUserName(currentUsername);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username);

        return  new UserPrincipal(user);
    }
}
