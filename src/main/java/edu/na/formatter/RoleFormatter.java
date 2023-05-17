package edu.na.formatter;

import edu.na.dto.DeviceDto;
import edu.na.dto.RoleDto;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
@Component
public class RoleFormatter implements Formatter<RoleDto> {

    @Override
    public RoleDto parse(String id, Locale locale) throws ParseException {
        RoleDto role = new RoleDto();
        role.setId(Long.parseLong(id));

        return role;
    }

    @Override
    public String print(RoleDto role, Locale locale) {
        return String.valueOf(role.getId());
    }
}