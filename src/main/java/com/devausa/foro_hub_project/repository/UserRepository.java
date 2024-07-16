package com.devausa.foro_hub_project.repository;

import com.devausa.foro_hub_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //metodo custom para buscar por email
//    Optional<User> findByEmail(String email);

    User findByEmail(String email);
}
