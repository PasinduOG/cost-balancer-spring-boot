package dev.pasindu.costbalancer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.pasindu.costbalancer.util.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String fullName;
    private String email;
    private String password;
    @JsonProperty("isCreatingNewFamily")
    private boolean isCreatingNewFamily;
    private String familyName;
    private Integer joinFamilyId;
    private Role role;
}