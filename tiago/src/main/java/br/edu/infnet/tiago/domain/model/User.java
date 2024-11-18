package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@With
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tb_users")
@Entity
public class User extends Person {

    @NotNull(message = "Username cannot be null.")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters.")
    private String username;

    @NotNull(message = "Password cannot be null.")
    @Size(min = 20, max = 50, message = "Password must be between 20 and 50 characters.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{20,50}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.")
    private String password;

    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "tb_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    @Size(max = 100, message = "The list of roles can contain a maximum of 100 items")
    private Set<UserRole> userRoles;

    private boolean active;
}