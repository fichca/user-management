package by.control.usermanagement.util;

import by.control.usermanagement.entity.Role;
import by.control.usermanagement.entity.UserStatus;

import java.util.Set;

public class ValidatorUtil {

    public static boolean validStatus(UserStatus userStatusUpdateDto, UserStatus userStatus) {
        if (userStatusUpdateDto != null) {
            return !userStatusUpdateDto.equals(userStatus);
        }
        return false;
    }

    public static boolean validRole(Role updateDtoRole, Set<Role> role) {
        if (updateDtoRole != null) {
            return !role.contains(updateDtoRole);
        }
        return false;
    }

    public static boolean validFirstName(String updateDtoFirstName, String firstName) {
        if (updateDtoFirstName != null) {
            if (!updateDtoFirstName.isEmpty()) {
                return !updateDtoFirstName.equals(firstName);
            }
        }
        return false;
    }

    public static boolean validLastName(String updateDtoLastName, String lastName) {
        if (updateDtoLastName != null) {
            if (!updateDtoLastName.isEmpty()) {
                return !updateDtoLastName.equals(lastName);
            }
        }
        return false;
    }

    public static boolean validFirstName(String firstName) {
        if (firstName != null) {
            if (!firstName.isEmpty()) {
                return firstName.matches("[A-Za-z]{1,16}$");
            }
        }
        return true;
    }

    public static boolean validLastName(String lastName) {
        if (lastName != null) {
            if (!lastName.isEmpty()) {
                return lastName.matches("[A-Za-z]{1,16}$");
            }
        }
        return true;
    }

    public static boolean validRole(String role) {
        if (role != null) {
            try {
                Role.valueOf(role);
            } catch (IllegalArgumentException e) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
