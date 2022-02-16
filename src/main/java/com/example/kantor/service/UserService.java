package com.example.kantor.service;

import com.example.kantor.exceptions.AddressNotFoundException;
import com.example.kantor.exceptions.UserNotFoundException;
import com.example.kantor.models.Address;
import com.example.kantor.models.User;
import com.example.kantor.repository.AddressRepository;
import com.example.kantor.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    final private UserRepository userRepository;
    final private AddressRepository addressRepository;

    public UserService(final UserRepository userRepository, final AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public void updateUser(User updatedUser) {
        User user = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new UserNotFoundException(updatedUser.getId()));

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setPhoneNumber(updatedUser.getPhoneNumber());

        userRepository.save(user);

    }

    public void addAddress(Integer id, Address newAddress) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        newAddress.setId(null);
        newAddress.setUser(user);
        addressRepository.save(newAddress);

    }

    public void deleteAddress(Integer userID, Integer addressID) {
        Address address = addressRepository.findById(addressID)
                .orElseThrow(() -> new AddressNotFoundException(addressID));

        if (Objects.equals(address.getUser().getId(), userID)) {
            addressRepository.delete(address);
        }

    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(User::getLastName))
                .toList();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Integer id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(userToDelete);
    }
}
