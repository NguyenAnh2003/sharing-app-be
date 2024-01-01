package com.example.socialapi.auth;

import com.example.socialapi.auth.dto.AuthDTO;
import com.example.socialapi.auth.dto.TokenDTO;
import com.example.socialapi.auth.requests.LoginRequest;
import com.example.socialapi.auth.requests.RegisterRequest;
import com.example.socialapi.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthController {

    private final AuthService authService;
    @Operation(responses = {
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Unauthorized",responseCode = "401")})
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
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping()
    public  ResponseEntity<AuthDTO> getCurrentUser()
    {
        /* using access token to get current user */
        return ResponseEntity.ok(authService.getCurrentUserService());
    }
}
