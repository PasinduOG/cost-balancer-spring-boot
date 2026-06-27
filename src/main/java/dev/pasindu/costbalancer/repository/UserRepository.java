package dev.pasindu.costbalancer.repository;

import dev.pasindu.costbalancer.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserByUsername(String username);
    boolean registerUser(User user);
}
