package by.control.usermanagement.service;

import by.control.usermanagement.dto.UserUpdateDto;
import by.control.usermanagement.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    List<User> getByRole(String role);

    List<User> getByUserName(String username);

    User getUserById(long id);

    boolean addUser(User user);

    boolean updateUser(long id, UserUpdateDto user);
}
