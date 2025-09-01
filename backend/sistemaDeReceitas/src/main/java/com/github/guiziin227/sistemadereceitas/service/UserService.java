package com.github.guiziin227.sistemadereceitas.service;

import com.github.guiziin227.sistemadereceitas.dtos.request.UserRequestDTO;
import com.github.guiziin227.sistemadereceitas.dtos.response.UserResponseDTO;
import com.github.guiziin227.sistemadereceitas.entities.Role;
import com.github.guiziin227.sistemadereceitas.entities.User;
import com.github.guiziin227.sistemadereceitas.repository.RoleRepository;
import com.github.guiziin227.sistemadereceitas.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser(UserRequestDTO user) {

        logger.info("Criando um usuario");
        var roleUser = roleRepository.findByName(Role.RoleName.ROLE_USER.name());

        var userExists = userRepository.findByEmail(user.email());
        if (userExists.isPresent()) {
            logger.warn("Tentativa de criar um usuário que já existe: {}", user.email());
            throw new RuntimeException("Usuário já existe");
        }

        var userToSave = new User();
        userToSave.setFirstName(user.firstName());
        userToSave.setEmail(user.email());
        userToSave.setPassword(passwordEncoder.encode(user.password()));
        userToSave.setRoles(Set.of(roleUser));

        logger.info("Usuário criado com sucesso: {}", user.email());
        userRepository.save(userToSave);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        logger.info("Buscando todos os usuários");
        return userRepository.findAll();
    }


}
