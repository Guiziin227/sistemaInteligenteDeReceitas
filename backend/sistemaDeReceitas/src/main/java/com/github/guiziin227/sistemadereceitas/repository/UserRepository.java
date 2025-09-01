package com.github.guiziin227.sistemadereceitas.repository;

import com.github.guiziin227.sistemadereceitas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByFirstName(String username);
    Optional<User> findByEmail(String email);
}
