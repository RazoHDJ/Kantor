package com.example.kantor.controller;

import com.example.kantor.models.Address;
import com.example.kantor.models.User;
import com.example.kantor.service.SecurityService;
import com.example.kantor.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    final private UserService userService;
    final private SecurityService securityService;

    public UserController(final UserService userService, final SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("") // profil użytkownika
    public String userProfile(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "user/users";
    }

    @GetMapping("/addForm")
    public String addUserForm(Model model){
        model.addAttribute("user", new User());

        return "user/user_edit";
    }

    @PostMapping("")
    public String addUserToDatabase(@ModelAttribute User user){
        userService.addUser(user);

        return "redirect:/user";
    }

/*
<a role="button" class="btn btn-success float-end" th:href="@{/user/addForm}">Dodaj</a>
<form th:action="@{'/user/{id}'(id=${user.id})}" th:method="delete">
<a role="button" class="btn btn-primary" th:href="@{'/user/{id}'(id=${user.id})}">Szczegóły</a>
*
*
* */

    @GetMapping("/address")
    public String createAddress(Model model) {
        model.addAttribute("address", new Address());

        return "add_address";
    }

    @PostMapping("/address")
    public String addAddress(Authentication authentication, @ModelAttribute Address newAddress) {
        securityService.getCurrentEmployee(authentication).ifPresent(user -> userService.addAddress(user.getId(), newAddress));

        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String editUser(Authentication authentication, Model model) {
        securityService.getCurrentEmployee(authentication).ifPresent(user -> model.addAttribute("user", user));

        return "user_edit";
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute User updatedUser) {
        userService.updateUser(updatedUser);

        return "redirect:/user";
    }

    @DeleteMapping("/address/{id}")
    public String deleteAddress(@PathVariable Integer id) {
        userService.deleteAddress(id);
        return "redirect:/user";
    }

}
