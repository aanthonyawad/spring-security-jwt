package com.awad.anthony.springsecurityjwt.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awad.anthony.springsecurityjwt.security.models.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByEmail(String email);

}
