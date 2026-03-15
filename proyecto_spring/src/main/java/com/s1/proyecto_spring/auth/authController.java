package com.s1.proyecto_spring.auth;

import com.s1.proyecto_spring.config.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class authController {
    private final JwtService jwtService;


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        // Validación
        if (username.equals("admin") && password.equals("1234")) {
            return jwtService.generateToken(username);
        }

        throw new BusinessRuleException("Credenciales inválidas");
    }    private final JwtService jwtService;


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        // Validación
        if (username.equals("admin") && password.equals("1234")) {
            return jwtService.generateToken(username);
        }

        throw new BusinessRuleException("Credenciales inválidas");
    }
}
