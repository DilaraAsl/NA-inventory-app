package edu.na.controller;

import edu.na.dto.RoleDto;
import edu.na.dto.UserDto;
import edu.na.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    String addNewUser(Model model) {
        model.addAttribute("user", new UserDto());

        return "/user/add";
    }

    @PostMapping("/add")
    String saveNewUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult) {
        boolean isUserNameUnique = userService.isUserNameUnique(userDto.getUser_name());
        if (!isUserNameUnique) {
            bindingResult.rejectValue("user_name", " ", "A user with this email already exists! Please enter another email ");
        }
        if (bindingResult.hasErrors()) {
            return "/user/add";
        }
        RoleDto role = new RoleDto();
        role.setId(3L);
        userDto.setRoleDto(role);
//        username and email are the same
        userDto.setEmail(userDto.getUser_name());
        userService.save(userDto);
        return "redirect:/users/list";
    }
    @GetMapping("/update/{id}")
    String updateUser(@PathVariable("id")Long id, Model model) {
        UserDto userDto=userService.findById(id);
        model.addAttribute("user", userDto);

        return "/user/update";
    }
    @PostMapping("/update/{id}")
    String saveUpdatedUser(@Valid @ModelAttribute("user") UserDto userDto,BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "/user/update";
        }
        RoleDto role = new RoleDto();
        role.setId(3L);
        userDto.setRoleDto(role);
//        username and email are the same
        userDto.setEmail(userDto.getUser_name());
        userService.save(userDto);
        return "redirect:/users/list";
    }
    @GetMapping("/delete/{id}")
    String deleteUser(@PathVariable("id")Long id){
       userService.delete(id);
       return "redirect:/users/list";
    }

    @GetMapping("/list")
    String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/user/list";
    }

}
