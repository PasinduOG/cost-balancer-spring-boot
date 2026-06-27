package dev.pasindu.costbalancer.service;

import dev.pasindu.costbalancer.dto.LoginRequest;
import dev.pasindu.costbalancer.dto.LoginResponse;
import dev.pasindu.costbalancer.dto.RegisterRequest;
import dev.pasindu.costbalancer.util.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface UserService {
    boolean registerUser(RegisterRequest request);
    LoginResponse loginUser(LoginRequest request);
    Role getRoleFromAuthorities(Collection<? extends GrantedAuthority> authorities);
}
