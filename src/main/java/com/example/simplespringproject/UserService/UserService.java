package com.example.simplespringproject.UserService;

import com.example.simplespringproject.Exception.UserException;
import com.example.simplespringproject.UserRepository.UserRepository;
import com.example.simplespringproject.User.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class UserService {

    private final UserRepository userRepository;

    private static final Logger log =
            LoggerFactory.getLogger(UserService.class);
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> getUserById(Integer id) {
        return userRepository.findById(id).switchIfEmpty(Mono.error(new UserException(id)));
    }


    public Mono<User> createUser(User user) {
        log.info("Creating user with email: {}", user.getEmail());
        user.setId(null);
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