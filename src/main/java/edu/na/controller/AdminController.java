package edu.na.controller;

import edu.na.dto.RoleDto;
import edu.na.dto.UserDto;
import edu.na.service.RoleService;
import edu.na.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/user")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String addNewUser(Model model){

        model.addAttribute("user",new UserDto());
        model.addAttribute("role",new RoleDto());
        model.addAttribute("roles",roleService.listAllRoles());
        return "/admins/add-user";
    }
    @PostMapping
    String saveNewUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult,@RequestParam("role") Long id) {
        boolean isUserNameUnique = userService.isUserNameUnique(userDto.getUser_name());
        if (!isUserNameUnique) {
            bindingResult.rejectValue("user_name", " ", "A user with this email already exists! Please enter another email ");
        }
        if (bindingResult.hasErrors()) {
            return "/admins/add-user";
        }
//        RoleDto role = new RoleDto();
//        role.setId(3L);
        RoleDto roleDto=roleService.findById(id);
        userDto.setRoleDto(roleDto);
//        username and email are the same
        userDto.setEmail(userDto.getUser_name());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userService.save(userDto);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/list")
    public String userList(Model model){
        model.addAttribute("users",userService.findAll());
        return "/admins/list-user";
    }
    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id")Long id, Model model){
        UserDto userDto=userService.findById(id);
        RoleDto roleDto=userDto.getRoleDto();
        model.addAttribute("user",userDto);
        model.addAttribute("role",roleDto);
        model.addAttribute("roles",roleService.listAllRoles());
        return "/admins/update-user";
    }
    @PostMapping("/update/{id}")
    public String saveUpdatedUser(@ModelAttribute("user") UserDto userDto, BindingResult bindingResult,@PathVariable("id") Long id){
        if (bindingResult.hasErrors()) {
            return "/admins/update-user";
        }
        userService.save(userDto);
        return "redirect:/admin/users/list";
    }
}
