package dev.pasindu.costbalancer.repository.impl;

import dev.pasindu.costbalancer.entity.User;
import dev.pasindu.costbalancer.repository.UserRepository;
import dev.pasindu.costbalancer.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate template;

    @Override
    public Optional<User> getUserByUsername(String username) {
        try {
            User user = template.queryForObject(
                    "SELECT * FROM users WHERE username = ?",
                    (rs, rowNum) -> new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("full_name"),
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            Role.valueOf(rs.getString("role")),
                            rs.getInt("family_id")
                    ),
                    username
            );
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean registerUser(User user) {
        return template.update(
                "INSERT INTO users (family_id, username, full_name, email, password_hash, role) VALUES (?,?,?,?,?,?)",
                user.getFamilyId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole() == null ? null : user.getRole().name()
        ) > 0;
    }
}