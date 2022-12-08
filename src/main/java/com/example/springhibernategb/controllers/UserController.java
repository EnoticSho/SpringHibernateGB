package com.example.springhibernategb.controllers;

import com.example.springhibernategb.entity.Role;
import com.example.springhibernategb.entity.Status;
import com.example.springhibernategb.entity.User;
import com.example.springhibernategb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('users')")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("role", Role.SUPERADMIN);
        System.out.println(userService);
        return "user/All_users";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('createUser')")
    public String formForNewUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", Arrays.stream(Role.values()).filter(a -> a != Role.SUPERADMIN).collect(Collectors.toList()));
        model.addAttribute("status", Status.values());
        return "user/form";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('createUser')")
    public String createUser(@ModelAttribute("user") User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user.toString());
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('createUser')")
    public String editUser(@PathVariable int id, Model model) {
        model.addAttribute("userEdit", userService.showById(id));
        model.addAttribute("roles", Arrays.stream(Role.values()).filter(a -> a != Role.SUPERADMIN).collect(Collectors.toList()));
        model.addAttribute("status", Status.values());
        return "user/edit_form";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('createUser')")
    private String deleteUser(@PathVariable int id) {
        System.out.println(userService);
        userService.deleteById1(id);
        return "redirect:/users";
    }

    @PatchMapping
    @PreAuthorize("hasAuthority('createUser')")
    private String update(@ModelAttribute("userEdit") User user) {
        System.out.println(user.toString());
        userService.saveUser(user);
        return "redirect:/users";
    }
}
