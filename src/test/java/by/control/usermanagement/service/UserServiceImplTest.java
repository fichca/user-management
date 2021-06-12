package by.control.usermanagement.service;

import by.control.usermanagement.entity.Role;
import by.control.usermanagement.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    void getByUserName(){
        String username = "test";
        List<User> byUserName = userService.getByUserName(username);
//        assertEquals(username, byUserName.get(1).getUsername());
    }


    @Test
    void getByRole(){
        String admin = "USER";
//        List<User> byRole = userService.getByRole(Role.valueOf(admin));

    }
}