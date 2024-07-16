package com.devausa.foro_hub_project.controller;

import com.devausa.foro_hub_project.dto.MessageDTO;
import com.devausa.foro_hub_project.dto.RegisterDTO;
import com.devausa.foro_hub_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageDTO> newRegister(@RequestBody RegisterDTO registerDTO){
        MessageDTO response = service.newUserRegister(registerDTO);
        //verifico el mensaje que retorna del servicio y si empieza con "Error"
        if (response.getMessage().startsWith("Error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
