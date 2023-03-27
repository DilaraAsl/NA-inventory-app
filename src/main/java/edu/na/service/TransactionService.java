package edu.na.service;

import edu.na.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    public List<TransactionDto> listAllTransactions();
    public TransactionDto findById(Long id);
}
