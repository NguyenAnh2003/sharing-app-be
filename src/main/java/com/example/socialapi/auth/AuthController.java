package com.example.socialapi.auth;

import com.example.socialapi.auth.dto.AuthDTO;
import com.example.socialapi.auth.requests.LoginRequest;
import com.example.socialapi.auth.requests.RegisterRequest;
import com.example.socialapi.common.ErrorResponse;
import com.example.socialapi.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

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
    public ResponseEntity<?> authenticate(
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
