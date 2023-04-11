package edu.na.service;

import edu.na.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto findById(Long id);

    List<RoleDto> listAllRoles();

    List<RoleDto> listRoleByLoggedInUser();
}
