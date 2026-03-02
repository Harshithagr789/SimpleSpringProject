package com.example.simplespringproject.UserService;

import com.example.simplespringproject.UserRepository.UserRepository;
import com.example.simplespringproject.User.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Mono<User> createUser(User user) {
        user.setId(null);  // Force INSERT
        return userRepository.save(user);
    }

    public Mono<User> updateUser(Integer id, User user) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                });
    }

    public Mono<Void> deleteUser(Integer id) {
        return userRepository.deleteById(id);
    }
}
