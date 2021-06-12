package by.control.usermanagement.controller;

import by.control.usermanagement.constant.Constants;
import by.control.usermanagement.dto.UserUpdateDto;
import by.control.usermanagement.entity.User;
import by.control.usermanagement.service.UserService;
import by.control.usermanagement.util.PageUtil;
import by.control.usermanagement.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("user")
public class UserController {

    private final static String ATTRIBUTE_USER = "user";
    private final static String ATTRIBUTE_USERS = "users";

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView getListUsers(ModelAndView modelAndView, @RequestParam(defaultValue = "0") int pageNumber) {
        List<User> allUsers = userService.getAllUsers();
        List<User> users = PageUtil.subListUsersByPageNumber(allUsers, pageNumber);
        int countUsersPage = PageUtil.getCountUsersPage(allUsers);
        modelAndView.addObject(ATTRIBUTE_USERS, users);
        modelAndView.addObject("countUsersPage", countUsersPage);
        modelAndView.addObject("pageNumber", pageNumber);
        modelAndView.setViewName("list");
        return modelAndView;
    }


    @GetMapping("username")
    public ModelAndView getListUsersByName(ModelAndView modelAndView, String username) {
        List<User> byUserName = userService.getByUserName(username);
        modelAndView.addObject(ATTRIBUTE_USERS, byUserName);
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @GetMapping("role")
    public ModelAndView getListUsersByRole(ModelAndView modelAndView, String role) {
        List<User> byRole = userService.getByRole(role);
        modelAndView.addObject(ATTRIBUTE_USERS, byRole);
        modelAndView.setViewName("list");
        return modelAndView;
    }


    @GetMapping("{id}")
    public ModelAndView getUserById(@PathVariable long id, ModelAndView modelAndView){
        User userById = userService.getUserById(id);
        modelAndView.addObject(ATTRIBUTE_USER, userById);
        modelAndView.setViewName("view");
        return modelAndView;
    }

    @GetMapping("/new")
    public String showAddUserForm(Model model) {
        model.addAttribute(ATTRIBUTE_USER, new User());
        return "new";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute(ATTRIBUTE_USER) @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "new";
        }
        String saveResult = "save";
        if (userService.addUser(user)) {
            model.addAttribute(saveResult, "Deal");
        } else {
            model.addAttribute(saveResult, "User name already exist");
        }
        return "new";
    }

    @GetMapping("{id}/edit")
    public String showUpdateUser(@PathVariable long id, Model model){
        User userById = userService.getUserById(id);
        model.addAttribute(ATTRIBUTE_USER, userById);

        return "edit";
    }

    @PostMapping("{id}/edit")
    public String updateUser(@PathVariable long id, UserUpdateDto userUpdateDto, Model model){

        if (!ValidatorUtil.validFirstName(userUpdateDto.getFirstName()) || !ValidatorUtil.validLastName(userUpdateDto.getLastName())){
            model.addAttribute("updateError", Constants.ERROR_VALIDATION_LANGUAGE + Constants.ERROR_VALIDATION_SIZE_1_16);
        }else {
            if (userService.updateUser(id, userUpdateDto)){
                return "redirect:/user/" + id;
            }else {
                model.addAttribute("updateError", "You haven't changed anything!");
            }
        }
        User userById = userService.getUserById(id);
        model.addAttribute(ATTRIBUTE_USER, userById);
        return "edit";
    }
}
