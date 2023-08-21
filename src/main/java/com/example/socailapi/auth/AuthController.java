package com.example.socailapi.auth;

import com.example.socailapi.auth.dto.AuthDTO;
import com.example.socailapi.auth.requests.LoginRequest;
import com.example.socailapi.auth.requests.RegisterRequest;
import com.example.socailapi.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthDTO> register(
            @RequestBody RegisterRequest req)
    {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthDTO> authenticate(
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
