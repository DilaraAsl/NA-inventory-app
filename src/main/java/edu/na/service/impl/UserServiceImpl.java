package edu.na.service.impl;

import edu.na.dto.RecordDto;
import edu.na.dto.UserDto;
import edu.na.entity.Record;
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
                .map(user->mapperUtil.convert(user,new UserDto()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto save(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto findById(Long id) {

        return mapperUtil.convert(userRepository.findById(id),new UserDto());
    }


    @Override
    public UserDto delete(Long id) {
        return null;
    }

    @Override
    public UserDto findByUserName(String userName) {

        return mapperUtil.convert(userRepository.findByUserName(userName),new UserDto());
    }


}
