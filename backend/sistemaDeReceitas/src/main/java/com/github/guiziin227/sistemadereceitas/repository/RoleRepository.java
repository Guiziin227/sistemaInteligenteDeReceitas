package com.github.guiziin227.sistemadereceitas.repository;

import com.github.guiziin227.sistemadereceitas.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
