package edu.na.service.impl;

import edu.na.dto.RoleDto;
import edu.na.repository.RoleRepository;
import edu.na.service.RoleService;
import edu.na.service.SecurityService;
import edu.na.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final MapperUtil mapperUtil;
    private final SecurityService securityService;

    public RoleServiceImpl(RoleRepository roleRepository, MapperUtil mapperUtil, SecurityService securityService) {
        this.roleRepository = roleRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
    }

    @Override
    public RoleDto findById(Long id) {
        return mapperUtil.convert(roleRepository.findById(id),new RoleDto());
    }

    @Override
    public List<RoleDto> listAllRoles() {
        return roleRepository.findAll().stream().map(role -> mapperUtil.convert(role,new RoleDto())).collect(Collectors.toList());
    }

    @Override
    public List<RoleDto> listRoleByLoggedInUser() {

        if (securityService.getLoggedInUser().getRoleDto().getId()== 1L) {
            return listAllRoles().stream().filter(roleDto -> roleDto.getId() == 2L).collect(Collectors.toList());
        }
        if (securityService.getLoggedInUser().getRoleDto().getId() == 2L) {
            return listAllRoles().stream().filter(roleDto -> roleDto.getId() != 1L).collect(Collectors.toList());
        }
        return null;
    }
}
