package com.example.socialapi.auth;

import com.example.socialapi.auth.dto.AuthDTO;
import com.example.socialapi.auth.dto.TokenDTO;
import com.example.socialapi.auth.requests.LoginRequest;
import com.example.socialapi.auth.requests.RegisterRequest;
import com.example.socialapi.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<TokenDTO> register(
            @RequestBody RegisterRequest req)
    {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> authenticate(
            @RequestBody LoginRequest req)
    {
        return ResponseEntity.ok(authService.login(req));
    }


    // getCurrentUser API
    @GetMapping()
    public  ResponseEntity<AuthDTO> getCurrentUser()
    {
        return ResponseEntity.ok(authService.getCurrentUserService());
    }
}
