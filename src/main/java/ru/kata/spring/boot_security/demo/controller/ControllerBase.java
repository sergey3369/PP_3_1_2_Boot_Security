package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class ControllerBase {
    final private PasswordEncoder passwordEncoder;
    final private RoleService roleService;
    final private UserService userService;

    public ControllerBase(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    String getAllUsersPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/admin/{id}")
    String getPage(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @GetMapping("/admin/new")
    String getAddPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("roles", roleService.getRole()
                .stream().collect(Collectors.toSet()));
        return "new";
    }

    @GetMapping("/admin/{id}/edit")
    String getEditPage(@PathVariable Integer id, ModelMap modelMap) {
        User user = userService.getUser(id);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", roleService.getRole()
                .stream().collect(Collectors.toSet()));
        return "edit";
    }

    @PostMapping("/")
    String add(@ModelAttribute("user") User user, @RequestParam(value = "role", defaultValue = "ROLE_USER") String[] role) {
        user.setRoles(roleService.getStringArrayToSetRole(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    String edit(@ModelAttribute("user") User user, @RequestParam(value = "role", defaultValue = "ROLE_USER") String[] role) {
        user.setRoles(roleService.getStringArrayToSetRole(role));
        if (!user.getPassword().equals(userService
                .getUser(Math.toIntExact(user.getId()))
                .getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
