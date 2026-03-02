package com.example.simplespringproject.UserRepository;

import com.example.simplespringproject.User.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

    Flux<User> findByName(String name);
}