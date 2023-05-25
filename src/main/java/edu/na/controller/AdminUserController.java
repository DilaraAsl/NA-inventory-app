package edu.na.controller;

import edu.na.dto.RoleDto;
import edu.na.dto.UserDto;
import edu.na.entity.Role;
import edu.na.service.RoleService;
import edu.na.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Id;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AdminUserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String addNewUser(Model model){
        RoleDto roleDto = new RoleDto();
        UserDto userDto=new UserDto();
        userDto.setRoleDto(roleDto);
        model.addAttribute("user",userDto);
        model.addAttribute("roles",roleService.listAllRoles());
        return "/admins/add-user";
    }
    @PostMapping
    String saveNewUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("roles",roleService.listAllRoles());
        boolean isUserNameUnique = userService.isUserNameUnique(userDto.getUser_name());

        if (!isUserNameUnique) {
            bindingResult.rejectValue("user_name", " ", "A user with this email already exists! Please enter another email ");
            return "/admins/add-user";
        }
        if (bindingResult.hasErrors()) {
            return "/admins/add-user";
        }

        if (userDto.getRoleDto().equals(null)) {
            // Handle the case when the role with the given ID is not found
            bindingResult.rejectValue("roleDto", " ", "Invalid role.");
            return "/admins/add-user";
        }
        try{
        RoleDto roleDto=roleService.findById(userDto.getRoleDto().getId());

        userDto.setRoleDto(roleDto);
//        username and email are the same
        userDto.setEmail(userDto.getUser_name());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userService.save(userDto);
            redirectAttributes.addFlashAttribute("successMessage", "User saved successfully!!!");
        return "redirect:/admin/user";
        }
        catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("user_name", "", "A user  with these credentials already exists!");
            return "/admins/add-user";
        }
    }


    @GetMapping("/list")
    public String userList(Model model){
        model.addAttribute("users",userService.findAll());
        return "/admins/list-user";
    }
    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id")Long id, Model model){
        UserDto userDto=userService.findById(id);
        RoleDto roleDto=userService.findRoleIdByUserName(userDto.getUser_name());
        userDto.setRoleDto(roleDto);
        model.addAttribute("user",userDto);
        model.addAttribute("roles",roleService.listAllRoles());
        return "/admins/update-user";
    }
    @PostMapping("/update/{id}")
    public String saveUpdatedUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult,@PathVariable("id") Long id, Model model,RedirectAttributes redirectAttributes){
        model.addAttribute("roles",roleService.listAllRoles());
        if (bindingResult.hasErrors()) {
            return "redirect:/admins/update/{id}";
        }
        userService.save(userDto);
        redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!!!");
        return "redirect:/admin/user/list";
    }
    @GetMapping("/delete/{id}")
    String deleteUser(@PathVariable("id")Long id){
        userService.delete(id);
        return "redirect:/admin/user/list";
    }

}
