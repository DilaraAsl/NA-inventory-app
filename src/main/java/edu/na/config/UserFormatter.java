package edu.na.config;

import edu.na.dto.UserDto;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
@Component
public class UserFormatter implements Formatter<UserDto> {
        @Override
        public UserDto parse(String id, Locale locale) throws ParseException {
            UserDto user = new UserDto();
            user.setId(Long.parseLong(id));

            return user;
        }

        @Override
        public String print(UserDto device, Locale locale) {
            return String.valueOf(device.getId());
        }
    }

