package edu.na.converter;

import edu.na.dto.DeviceDto;
import edu.na.service.DeviceService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class DeviceDtoConverter implements Converter <String, DeviceDto>{
private final DeviceService deviceService;

    public DeviceDtoConverter(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Override
    public DeviceDto convert(String source) {
        if(source.equals(null)||source.isBlank())
        return null;
        return deviceService.findDevice(Long.parseLong(source));
    }
}
