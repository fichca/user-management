package by.control.usermanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Pattern(regexp = "[A-Za-z]{3,16}$", message =  "Only Latin characters and numbers.")
    @Size( min = 3, max = 16, message = "Ь")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,16}$", message = "Only Latin characters and numbers. At least one digit and symbol.")
    @Size( min = 3, max = 16, message = "Minimum length 3. Maximum length 16.")
    private String password;

    @NotBlank
    @Pattern(regexp = "[A-Za-z]{1,16}$", message =  "Only Latin characters and numbers.")
    @Size(min = 1, max = 16, message = "Minimum length 1. Maximum length 16.")
    private String firstName;


    @NotBlank
    @Pattern(regexp = "[A-Za-z]{1,16}$", message =  "Only Latin characters and numbers.")
    @Size( min = 1, max = 16, message = "Minimum length 1. Maximum length 16.")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    private LocalDateTime date;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userStatus.equals(UserStatus.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    void prePersist() {
        date = LocalDateTime.now();
    }
}
