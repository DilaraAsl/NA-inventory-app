package edu.na.service.impl;

import edu.na.dto.RecordDto;
import edu.na.dto.UserDto;
import edu.na.entity.Record;
import edu.na.entity.User;
import edu.na.exceptions.UserNotFoundException;
import edu.na.repository.UserRepository;
import edu.na.service.UserService;
import edu.na.util.MapperUtil;
import org.hibernate.engine.query.spi.ReturnMetadata;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDto> findAll() {

        return userRepository.findAll().stream()
                .map(user -> mapperUtil.convert(user, new UserDto()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto save(UserDto userDto) {

        User user = mapperUtil.convert(userDto, new User());
        userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public UserDto findById(Long id) {

        return mapperUtil.convert(userRepository.findById(id), new UserDto());
    }


    @Override
    public UserDto delete(Long id) {
        User user=userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User "+id+" does not exist."));
        user.setIsDeleted(true);
        userRepository.save(user);
        return mapperUtil.convert(user,new UserDto());
    }

    @Override
    public UserDto findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) return null; // if the user is null we are not passing it to the model mapper
        //if it is not null then we are converting it to a dto
        return mapperUtil.convert(user, new UserDto());
    }

    @Override
    public boolean isUserNameUnique(String userName) {
        UserDto userDto = findByUserName(userName);
        if (userDto == null || userDto.getUser_name() == null) return true;
        return false;
    }


}
