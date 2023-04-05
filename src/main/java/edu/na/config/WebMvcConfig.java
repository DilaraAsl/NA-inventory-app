package edu.na.config;

import edu.na.converter.DeviceDtoConverter;
import edu.na.converter.StringToDeviceConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    private final StringToDeviceConverter stringToDeviceConverter;
//
//    public WebMvcConfig(StringToDeviceConverter stringToDeviceConverter) {
//        this.stringToDeviceConverter = stringToDeviceConverter;
//    }
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(stringToDeviceConverter);
//    }
//}

//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//
//    private final StringToDeviceConverter stringToDeviceConverter;
//    private final DeviceDtoConverter deviceDtoToStringConverter;
//
//    public WebMvcConfig(StringToDeviceConverter stringToDeviceConverter, DeviceDtoConverter deviceDtoToStringConverter) {
//        this.stringToDeviceConverter = stringToDeviceConverter;
//        this.deviceDtoToStringConverter = deviceDtoToStringConverter;
//    }
//
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(stringToDeviceConverter);
//        registry.addConverter(deviceDtoToStringConverter);
//    }
}
