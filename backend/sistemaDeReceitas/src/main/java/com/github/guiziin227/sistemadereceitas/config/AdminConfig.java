package com.github.guiziin227.sistemadereceitas.config;

import com.github.guiziin227.sistemadereceitas.entities.Role;
import com.github.guiziin227.sistemadereceitas.entities.User;
import com.github.guiziin227.sistemadereceitas.repository.RoleRepository;
import com.github.guiziin227.sistemadereceitas.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Set;

@Configuration
public class AdminConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminConfig(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.RoleName.ROLE_ADMIN.name());
        System.out.println(roleAdmin);

        var userAdmin = userRepository.findByEmail("admin@admin.com");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Admin user already exists.");
                },
                () -> {
                    var adminUser = new User();
                    adminUser.setFirstName("admin");
                    adminUser.setEmail("admin@admin.com");
                    adminUser.setPassword(passwordEncoder.encode("admin123"));
                    adminUser.setRoles(Set.of(roleAdmin));
                    userRepository.save(adminUser);
                    System.out.println("Admin user created.");
                }
        );



    }
}
