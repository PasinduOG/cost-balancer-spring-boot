package dev.pasindu.costbalancer.service;

import dev.pasindu.costbalancer.exception.UserNotFoundException;
import dev.pasindu.costbalancer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) {
        return repository.getUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }
}