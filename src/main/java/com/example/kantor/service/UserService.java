package com.example.kantor.service;

import com.example.kantor.models.Address;
import com.example.kantor.models.User;
import com.example.kantor.repository.AddressRepository;
import com.example.kantor.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    final private UserRepository userRepository;
    final private AddressRepository addressRepository;

    public UserService(final UserRepository userRepository, final AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public void updateUser(User updatedUser) {
        User userToUpdate = userRepository.getById(updatedUser.getId());
        userToUpdate.setFirstName(updatedUser.getFirstName());
        userToUpdate.setLastName(updatedUser.getLastName());
        userToUpdate.setPhoneNumber(updatedUser.getPhoneNumber());

        userRepository.save(userToUpdate);
    }

    public void addAddress(Integer id, Address newAddress) {

        userRepository.findById(id).ifPresent(user -> {
            newAddress.setUser(user);
            addressRepository.save(newAddress);
        });
    }

    public void deleteAddress(Integer addressID) {
        addressRepository.findById(addressID).ifPresent(addressRepository::delete);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(User::getLastName))
                .toList();
    }

    public void addUser(User user) {

    }
}
