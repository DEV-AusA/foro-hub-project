package com.devausa.foro_hub_project.repository;

import com.devausa.foro_hub_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
