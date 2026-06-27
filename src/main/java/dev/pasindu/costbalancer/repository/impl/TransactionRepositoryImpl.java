package dev.pasindu.costbalancer.repository.impl;

import dev.pasindu.costbalancer.entity.Transaction;
import dev.pasindu.costbalancer.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    private final JdbcTemplate template;

    @Override
    public boolean addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (user_id, category_id, amount, transaction_date, transaction_time, note) VALUES (?, ?, ?, ?, ?, ?)";
        return template.update(sql,
                transaction.getUserId(),
                transaction.getCategoryId(),
                transaction.getAmount(),
                transaction.getTransactionDate() != null ? Date.valueOf(transaction.getTransactionDate()) : null,
                transaction.getTransactionTime() != null ? Time.valueOf(transaction.getTransactionTime()) : null,
                transaction.getNote()
        ) > 0;
    }

    @Override
    public BigDecimal getTotalBalanceByFamilyId(Integer familyId) {
        String sql = "SELECT " +
                "COALESCE(SUM(IF(c.type = 'INCOME', t.amount, 0)), 0) - " +
                "COALESCE(SUM(IF(c.type = 'EXPENSE', t.amount, 0)), 0) AS balance " +
                "FROM transactions t " +
                "JOIN users u ON t.user_id = u.id " +
                "JOIN categories c ON t.category_id = c.id " +
                "WHERE u.family_id = ?";
        return template.queryForObject(sql, BigDecimal.class, familyId);
    }

    @Override
    public List<Map<String, Object>> getTransactionHistory(Integer familyId) {
        String sql = "SELECT t.amount, t.note, t.transaction_date, t.transaction_time, u.full_name as user_name, c.type as category_type " +
                "FROM transactions t " +
                "JOIN users u ON t.user_id = u.id " +
                "JOIN categories c ON t.category_id = c.id " +
                "WHERE u.family_id = ? ORDER BY t.id DESC";
        return template.queryForList(sql, familyId);
    }
}