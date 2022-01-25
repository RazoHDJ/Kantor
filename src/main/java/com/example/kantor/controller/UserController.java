package com.example.kantor.controller;

import com.example.kantor.models.Address;
import com.example.kantor.models.User;
import com.example.kantor.service.SecurityService;
import com.example.kantor.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    final private UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("") // wszyscy użytkownicy
    public String userProfile(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "user/users";
    }

    @GetMapping("/{id}") // profil użytkownika
    public String userProfile(@PathVariable Integer id, Model model) {
        userService.getUser(id).ifPresent(user -> model.addAttribute("user", user));

        return "user/user_preview";
    }

    @GetMapping("/addForm") // formularz dodawania użytkownika
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());

        return "user/user_add";
    }

    @GetMapping("/edit/{id}") // edytowanie konkretnego użytkownika
    public String editUser(@PathVariable Integer id, Model model) {
        userService.getUser(id).ifPresent(user -> model.addAttribute("user", user));

        return "user/user_edit";
    }

    @PostMapping("") //dodawanie do bazy danych użytkownika
    public String addUserToDatabase(@ModelAttribute User user) {
        userService.addUser(user);

        return "redirect:/user";
    }

    @PostMapping("/{id}") // update użytkownika w bazie danych
    public String updateUserInDatabase(@ModelAttribute User user, @PathVariable Integer id) {
        user.setId(id);
        userService.updateUser(user);

        return ("redirect:/user/"+id.toString());
    }

    @DeleteMapping("/{id}") //usuwanie użytkownika
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }


    @GetMapping("/{id}/address") // fomularz dodawania użytkownika
    public String openAddressForm(@PathVariable Integer id, Model model) {
        model.addAttribute("address", new Address());
        model.addAttribute("userID", id);

        return "user/add_address";
    }

    @PostMapping("/{id}/address") // dodawanie nowego adresu do użytkownika
    public String addAddress(@PathVariable Integer id, @ModelAttribute Address newAddress) {
        userService.addAddress(id, newAddress);

        return ("redirect:/user/" + id.toString());
    }


    @DeleteMapping("/{userID}/address/{addressID}") // usuwanie adresu użytkownika
    public String deleteAddress(@PathVariable Integer addressID, @PathVariable Integer userID) {
        userService.deleteAddress(userID, addressID);
        return ("redirect:/user/" + userID.toString());
    }

}
