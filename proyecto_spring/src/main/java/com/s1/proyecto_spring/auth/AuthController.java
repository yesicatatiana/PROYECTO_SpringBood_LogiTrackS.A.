package com.s1.proyecto_spring.auth;

import com.s1.proyecto_spring.config.JwtService;
import com.s1.proyecto_spring.exception.BusinessRulesException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;


    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        if (request.usermane().equals("admin") &&
                request.password().equals("1234")) {

            String token = jwtService.generateToken(request.usermane());
            return Map.of("token", token);
        }

        throw new BusinessRulesException("Credenciales inválidas");
    }
}

