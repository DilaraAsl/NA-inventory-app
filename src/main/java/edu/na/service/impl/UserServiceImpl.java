package edu.na.service.impl;

import edu.na.dto.RoleDto;
import edu.na.dto.UserDto;
import edu.na.entity.Role;
import edu.na.entity.User;
import edu.na.exceptions.UserNotFoundException;
import edu.na.repository.UserRepository;
import edu.na.service.RecordService;
import edu.na.service.SecurityService;
import edu.na.service.UserService;
import edu.na.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RecordService recordService;

    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, RecordService recordService, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.recordService = recordService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDto> findAll() {

        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(User::getFirst_name))
                .map(user -> mapperUtil.convert(user, new UserDto()))
                .collect(Collectors.toList());
    }
    @Override
    public List<UserDto> userListOrderedByUserName() {

        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(User::getUser_name))
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
//        UserDto userDto=findById(id);

        // if user has an item in her/his position then user cannot be deleted
//        if transcation is not complete then the user has items user cannot be deleted
    // if the user has no records, if the record is null
        if(recordService.isTransactionCompleteByUser(user.getId()) == null || recordService.isTransactionCompleteByUser(user.getId())){
        user.setIsDeleted(true);
        userRepository.save(user);}
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

    @Override
    public String findRoleByUserName(String userName) {
        Role role=userRepository.retrieveRoleOfUser(userName);
        return role !=null ? role.getDescription() : null;

    }

    @Override
    public RoleDto findRoleIdByUserName(String userName) {
        Role role=userRepository.retrieveRoleOfUser(userName);
        return role !=null ? mapperUtil.convert(role,new RoleDto()) : null;
    }


}
