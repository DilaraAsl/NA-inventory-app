package edu.na.formatter;

import edu.na.dto.DeviceDto;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
@Component
public class DeviceFormatter implements Formatter <DeviceDto>{

    @Override
    public DeviceDto parse(String id, Locale locale) throws ParseException {
        DeviceDto device = new DeviceDto();
        device.setId(Long.parseLong(id));

        return device;
    }

    @Override
    public String print(DeviceDto device, Locale locale) {
        return String.valueOf(device.getId());
    }
}
