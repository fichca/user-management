package by.control.usermanagement.dto;

import by.control.usermanagement.entity.Role;
import by.control.usermanagement.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private UserStatus userStatus;
    private Role role;
}
