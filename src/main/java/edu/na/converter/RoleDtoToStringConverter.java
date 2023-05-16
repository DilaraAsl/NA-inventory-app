package edu.na.converter;

import edu.na.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class RoleDtoToStringConverter implements Converter<RoleDto,String> {
    @Override
    public String convert(RoleDto source) {
        return source.getDescription();
    }
}