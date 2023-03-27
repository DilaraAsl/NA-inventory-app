package edu.na.converter;

import edu.na.dto.RecordDto;
import edu.na.service.RecordService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecordDtoConverter implements Converter <String, RecordDto>{
private final RecordService recordService;

    public RecordDtoConverter(RecordService recordService) {
        this.recordService = recordService;
    }

    @Override
    public RecordDto convert(String source) {
        if(source==null || source.isBlank())
        return null;
        return recordService.findById(Long.parseLong(source));
    }
}
