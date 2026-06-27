package dev.pasindu.costbalancer.service.impl;

import dev.pasindu.costbalancer.dto.LoginRequest;
import dev.pasindu.costbalancer.dto.LoginResponse;
import dev.pasindu.costbalancer.dto.RegisterRequest;
import dev.pasindu.costbalancer.entity.User;
import dev.pasindu.costbalancer.repository.FamilyRepository;
import dev.pasindu.costbalancer.repository.UserRepository;
import dev.pasindu.costbalancer.service.JwtService;
import dev.pasindu.costbalancer.service.UserService;
import dev.pasindu.costbalancer.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationProvider authenticationProvider;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    @Transactional
    public boolean registerUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        if (request.isCreatingNewFamily()) {
            Integer newFamilyId = familyRepository.createFamilyGroup(request.getFamilyName());
            user.setFamilyId(newFamilyId);
            user.setRole(Role.PARENT);
        } else {
            user.setFamilyId(request.getJoinFamilyId());
            user.setRole(request.getRole() != null ? request.getRole() : Role.MEMBER);
        }

        return userRepository.registerUser(user);
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails details = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(details);
        Role role = getRoleFromAuthorities(details.getAuthorities());

        Integer familyId = null;
        Integer userId = null;
        String fullName = null;
        String familyName = null;

        if (details instanceof User customUser) {
            familyId = customUser.getFamilyId();
            userId = customUser.getId();
            fullName = customUser.getFullName();

            if (familyId != null) {
                familyName = familyRepository.getFamilyName(familyId);
            }
        }
        return new LoginResponse(token, role, familyId, userId, fullName, familyName);
    }

    @Override
    public Role getRoleFromAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .findFirst()
                .map(a -> Role.valueOf(a.getAuthority()))
                .orElse(Role.MEMBER);
    }
}