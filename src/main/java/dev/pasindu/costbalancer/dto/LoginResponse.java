package dev.pasindu.costbalancer.dto;

import dev.pasindu.costbalancer.util.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private String token;
    private Role role;
    private Integer familyId;
    private Integer userId;
    private String fullName;
    private String familyName;
}
