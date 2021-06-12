package by.control.usermanagement.service;

import by.control.usermanagement.dto.UserUpdateDto;
import by.control.usermanagement.entity.Role;
import by.control.usermanagement.entity.User;
import by.control.usermanagement.entity.UserStatus;
import by.control.usermanagement.repository.UserRepository;
import by.control.usermanagement.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getByRole(String role) {
        if (ValidatorUtil.validRole(role)){
            return userRepository.findByRole(Role.valueOf(role));
        }
        return new ArrayList<>();
    }

    @Override
    public List<User> getByUserName(String username) {
        List<User> all = userRepository.findAll();
        List<User> byUsername = new ArrayList<>();
        Pattern p = Pattern.compile("(\\w)*"+ username + "(\\w)*");
        for (User user : all) {
            if (p.matcher(user.getUsername()).matches()){
                byUsername.add(user);
            }
        }
        return byUsername;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public boolean addUser(User user) {
        if (!userRepository.existsByUsername(user.getUsername())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(long id, UserUpdateDto user) {
        User byId = userRepository.getById(id);
        if (updateUser(byId, user)){
            userRepository.save(byId);
            return true;
        }
        return false;
    }

    private boolean updateUser(User user, UserUpdateDto updateDto){
        boolean isUpdate = false;

        String updateDtoFirstName = updateDto.getFirstName();
        String updateDtoLastName = updateDto.getLastName();
        Role updateDtoRole = updateDto.getRole();
        UserStatus updateDtoStatus = updateDto.getUserStatus();


        if (ValidatorUtil.validFirstName(updateDtoFirstName, user.getFirstName())){
            user.setFirstName(updateDtoFirstName);
           isUpdate = true;
        }
        if (ValidatorUtil.validLastName(updateDtoLastName, user.getLastName())){
            user.setFirstName(updateDtoLastName);
            isUpdate = true;
        }

        if (ValidatorUtil.validRole(updateDtoRole, user.getRole())){
            user.getRole().add(updateDtoRole);
            isUpdate = true;
        }
        if (ValidatorUtil.validStatus(updateDtoStatus, user.getUserStatus())) {
            user.setUserStatus(updateDtoStatus);
            isUpdate = true;
        }
        return isUpdate;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.get();
    }
}
