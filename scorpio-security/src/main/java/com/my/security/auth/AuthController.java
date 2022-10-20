package com.my.security.auth;

import com.my.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final SecurityService securityService;

    @Autowired
    public AuthController(@Qualifier("AdminServiceImpl") SecurityService securityService) {
        this.securityService = securityService;
    }


    @PostMapping
    public ResponseEntity<Map<String, String>> generateToken(@RequestBody AuthRequest request) {
        String token = securityService.login(request.getUsername(), request.getPassword());
        Map<String, String> response = Collections.singletonMap("token", token);

        return ResponseEntity.ok(response);
    }
}
