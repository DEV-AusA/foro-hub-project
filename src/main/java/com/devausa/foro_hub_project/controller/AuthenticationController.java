package com.devausa.foro_hub_project.controller;

import com.devausa.foro_hub_project.dto.AuthenticateData;
import com.devausa.foro_hub_project.dto.JWTData;
import com.devausa.foro_hub_project.infra.security.TokenService;
import com.devausa.foro_hub_project.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity realizarLogin(
            @RequestBody
            @Valid
            AuthenticateData datosAutenticacion
    ) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.email(), datosAutenticacion.password());
        var autenticacion = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((User) autenticacion.getPrincipal());
        return ResponseEntity.ok(new JWTData(tokenJWT));
    }
}
