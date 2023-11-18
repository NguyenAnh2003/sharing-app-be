package com.example.socialapi.auth;

import com.example.socialapi.auth.dto.AuthDTO;
import com.example.socialapi.auth.dto.TokenDTO;
import com.example.socialapi.auth.requests.LoginRequest;
import com.example.socialapi.auth.requests.RegisterRequest;
import com.example.socialapi.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest req)
    {
        /* return status */
        UserDTO result = authService.register(req);
        if(result != null) {
            return new ResponseEntity<>("Register successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> authenticate(
            @RequestBody LoginRequest req)
    {
        /* return access token */
        return ResponseEntity.ok(authService.login(req));
    }


    // getCurrentUser API
    @GetMapping()
    public  ResponseEntity<AuthDTO> getCurrentUser()
    {
        /* using access token to get current user */
        return ResponseEntity.ok(authService.getCurrentUserService());
    }
}
