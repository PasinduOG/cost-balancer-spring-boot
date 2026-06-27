package dev.pasindu.costbalancer.repository.impl;

import dev.pasindu.costbalancer.repository.FamilyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class FamilyRepositoryImpl implements FamilyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer createFamilyGroup(String familyName) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO family_groups (name) VALUES (?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, familyName);
            return ps;
        }, keyHolder);

        Number generatedKey = keyHolder.getKey();
        return generatedKey != null ? generatedKey.intValue() : null;
    }

    @Override
    public String getFamilyName(Integer familyId) {
        String sql = "SELECT name FROM family_groups WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, familyId);
    }
}