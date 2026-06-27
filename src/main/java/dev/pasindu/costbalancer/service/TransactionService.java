package dev.pasindu.costbalancer.service;

import dev.pasindu.costbalancer.entity.Transaction;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TransactionService {
    boolean addTransaction(Transaction transaction);
    BigDecimal calculateBalance(Integer familyId);
    List<Map<String, Object>> getTransactionHistory(Integer familyId);
}