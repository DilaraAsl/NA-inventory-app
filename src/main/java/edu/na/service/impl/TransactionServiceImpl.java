package edu.na.service.impl;

import edu.na.dto.TransactionDto;
import edu.na.repository.TransactionRepository;
import edu.na.service.TransactionService;
import edu.na.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final MapperUtil mapperUtil;

    public TransactionServiceImpl(TransactionRepository transactionRepository, MapperUtil mapperUtil) {
        this.transactionRepository = transactionRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<TransactionDto> listAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(transaction -> mapperUtil.convert(transaction,new TransactionDto()))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Long id) {
        return mapperUtil.convert(transactionRepository.findById(id),new TransactionDto());
    }
}
