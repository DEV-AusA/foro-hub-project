package com.devausa.foro_hub_project.service;

import com.devausa.foro_hub_project.dto.MessageDTO;
import com.devausa.foro_hub_project.dto.RegisterDTO;
import com.devausa.foro_hub_project.model.User;
import com.devausa.foro_hub_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MessageDTO newUserRegister(RegisterDTO registerDTO) {
        //busco por email
        Optional<User> userFinded = Optional.ofNullable(userRepository.findByEmail(registerDTO.email()));

        if (userFinded.isPresent()) {
            return new MessageDTO("Error ya existe alguien registrado con ese correo");
        }

        User user = new User();
        user.setName(registerDTO.name());
        user.setLastName(registerDTO.lastName());
        user.setTelephone(registerDTO.telephone());
        user.setEmail(registerDTO.email());
        //encripto la pass con bcrypt
        user.setPassword(passwordEncoder.encode(registerDTO.password()));

        userRepository.save(user);
        return new MessageDTO("Gracias por registrarte, ¡Bienvenido al Foro!");
    }
}
