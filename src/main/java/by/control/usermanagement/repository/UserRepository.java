package by.control.usermanagement.repository;

import by.control.usermanagement.entity.Role;
import by.control.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(Role role);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
