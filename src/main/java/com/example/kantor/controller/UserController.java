package com.example.kantor.controller;

import com.example.kantor.exceptions.UserCouldNotBeAdded;
import com.example.kantor.exceptions.UserNotFoundException;
import com.example.kantor.models.Address;
import com.example.kantor.models.User;
import com.example.kantor.service.ExchangeService;
import com.example.kantor.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    final private UserService userService;
    final private ExchangeService exchangeService;

    public UserController(final UserService userService, final ExchangeService exchangeService) {
        this.userService = userService;
        this.exchangeService = exchangeService;
    }

    @GetMapping("") // wszyscy użytkownicy
    public String userProfile(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "user/users";
    }

    @GetMapping("/{id}") // profil użytkownika
    public String userProfile(@PathVariable Integer id, Model model) {
        User user = userService.getUser(id).orElseThrow(() -> new UserNotFoundException(id));

        model.addAttribute("user", user);
        model.addAttribute("exchangeList", exchangeService.getUserExchanges(id));

        return "user/user_preview";
    }

    @GetMapping("/addForm") // formularz dodawania użytkownika
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());

        return "user/user_add";
    }

    @GetMapping("/edit/{id}") // edytowanie konkretnego użytkownika
    public String editUser(@PathVariable Integer id, Model model) {
        User user = userService.getUser(id).orElseThrow(() -> new UserNotFoundException(id));
        model.addAttribute("user", user);

        return "user/user_edit";
    }

    @PostMapping("") //dodawanie do bazy danych użytkownika
    public String addUserToDatabase(@ModelAttribute @Valid User user) {
        userService.addUser(user);

        return "redirect:/user";
    }

    @PostMapping("/{id}") // update użytkownika w bazie danych
    public String updateUserInDatabase(@Valid @ModelAttribute User user, @PathVariable Integer id) {
        user.setId(id);
        userService.updateUser(user);

        return ("redirect:/user/" + id.toString());
    }

    @DeleteMapping("/{id}") //usuwanie użytkownika
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }


    @GetMapping("/{id}/address") // fomularz dodawania adresu
    public String openAddressForm(@PathVariable Integer id, Model model) {
        model.addAttribute("address", new Address());
        model.addAttribute("userID", id);

        return "user/add_address";
    }

    @PostMapping("/{id}/address") // dodawanie nowego adresu do użytkownika
    public String addAddress(@PathVariable Integer id, @Valid @ModelAttribute Address newAddress) {
        userService.addAddress(id, newAddress);

        return ("redirect:/user/" + id.toString());
    }


    @DeleteMapping("/{userID}/address/{addressID}") // usuwanie adresu użytkownika
    public String deleteAddress(@PathVariable Integer addressID, @PathVariable Integer userID) {
        userService.deleteAddress(userID, addressID);
        return ("redirect:/user/" + userID.toString());
    }

}
