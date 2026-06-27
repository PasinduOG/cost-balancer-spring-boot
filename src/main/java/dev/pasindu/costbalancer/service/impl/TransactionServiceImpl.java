package dev.pasindu.costbalancer.service.impl;

import dev.pasindu.costbalancer.entity.Transaction;
import dev.pasindu.costbalancer.repository.TransactionRepository;
import dev.pasindu.costbalancer.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    @Override
    public boolean addTransaction(Transaction transaction) {
        return repository.addTransaction(transaction);
    }

    @Override
    public BigDecimal calculateBalance(Integer familyId) {
        return repository.getTotalBalanceByFamilyId(familyId);
    }

    @Override
    public List<Map<String, Object>> getTransactionHistory(Integer familyId) {
        return repository.getTransactionHistory(familyId);
    }
}