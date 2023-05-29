package com.example.newsfeedapi.auth;

import com.example.newsfeedapi.auth.requests.LoginRequest;
import com.example.newsfeedapi.auth.requests.RegisterRequest;
import com.example.newsfeedapi.user.User;
import com.example.newsfeedapi.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest req)
    {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody LoginRequest req)
    {
        return ResponseEntity.ok(authService.login(req));
    }

    // getCurrentUser API
    @GetMapping()
    public  ResponseEntity<UserDTO> getCurrentUser()
    {
        return ResponseEntity.ok(authService.getCurrentUserService());
    }
}
