package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
    	System.out.println(authRequest+"-----------------------------------------");
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    	//return "Test String";
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
    	System.out.println("Validating Token: "+token);
        service.validateToken(token);
        System.out.println("Token validation done");
        return "Token is valid";
    }

}
