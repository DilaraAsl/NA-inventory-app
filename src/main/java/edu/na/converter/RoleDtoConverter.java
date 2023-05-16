package edu.na.converter;

import edu.na.dto.RoleDto;
import edu.na.service.RoleService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Component
public class RoleDtoConverter implements Converter <String, RoleDto>{
    private final RoleService roleService;

    public RoleDtoConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDto convert(String id) {
        if(id==null||id.isBlank())
            return null;
        return roleService.findById(Long.parseLong(id));
    }
}
