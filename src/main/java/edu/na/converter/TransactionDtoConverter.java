package edu.na.converter;

import edu.na.dto.TransactionDto;
import edu.na.service.TransactionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter implements Converter <String, TransactionDto>{
    private final TransactionService transactionService;

    public TransactionDtoConverter(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public TransactionDto convert(String source) {
        if(source.equals(null)||source.isBlank()) return null;
        return transactionService.findById(Long.parseLong(source));

    }
}
