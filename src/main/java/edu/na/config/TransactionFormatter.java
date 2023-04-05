package edu.na.config;

import edu.na.dto.TransactionDto;
import edu.na.dto.TransactionDto;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class TransactionFormatter implements Formatter<TransactionDto> {
    @Override
    public TransactionDto parse(String id, Locale locale) throws ParseException {
        TransactionDto transaction = new TransactionDto();
        transaction.setId(Long.parseLong(id));

        return transaction;
    }

    @Override
    public String print(TransactionDto device, Locale locale) {
        return String.valueOf(device.getId());
    }
}
