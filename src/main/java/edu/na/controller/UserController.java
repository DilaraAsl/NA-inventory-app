package edu.na.controller;

import edu.na.dto.RoleDto;
import edu.na.dto.UserDto;
import edu.na.service.RoleService;
import edu.na.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/add")
    String addNewUser(Model model) {
        UserDto userDto = new UserDto();
        RoleDto role = new RoleDto();
        role.setId(3L);
        role.setDescription("Employee");
        userDto.setRoleDto(role);
        model.addAttribute("user", userDto);
//        model.addAttribute("roles",roleService.listAllRoles());

        return "/user/add";
    }

    @PostMapping("/add")
    String saveNewUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        boolean isUserNameUnique = userService.isUserNameUnique(userDto.getUser_name());
        if (!isUserNameUnique) {
            bindingResult.rejectValue("user_name", "", "An assignee with this email already exists! Please enter another email");
        }

        if (bindingResult.hasErrors()) {
            return "/user/add";
        }

        try {
            userDto.setEmail(userDto.getUser_name());
            userService.save(userDto);

//        model.addAttribute("successMessage", "User saved successfully");
            redirectAttributes.addFlashAttribute("successMessage", "Assignee saved successfully!!!");
            return "redirect:/users/add";
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("user_name", "", "An assignee with these credentials already exists!");
            return "/user/add";
        }

    }

    @GetMapping("/update/{id}")
    String updateUser(@PathVariable("id") Long id, Model model) {
        UserDto userDto = userService.findById(id);
        model.addAttribute("user", userDto);

        return "/user/update";
    }

    @PostMapping("/update/{id}")
    String saveUpdatedUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, @PathVariable("id") Long id) {
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
    String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users/list";
    }

    @GetMapping("/list")
    String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/user/list";
    }

}
