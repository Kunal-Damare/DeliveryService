package com.vw.deliveryservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vw.deliveryservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByName(String name); // Find user by email

    boolean existsByEmail(String email); // Check if user exists

	Optional<User> findById(int id);

}
