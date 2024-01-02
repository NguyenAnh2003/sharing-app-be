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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthController {

    private final AuthService authService;
    private static final Logger logging = LoggerFactory.getLogger(AuthController.class);

    /* swagger config */
    @Operation(responses = {
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Unauthorized",responseCode = "401")})

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        try {
            logging.info("register method");
            UserDTO result = authService.register(req.getName(), req.getGmail(), req.getPassword(),
                                                    req.getAvatarURL(), req.getGender());
            return new ResponseEntity<>("Register successfully", HttpStatus.OK);
        } catch (Exception e) {
            logging.error("cannot register b.c invalid info");
            return new ResponseEntity("Cannot register", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> authenticate(@RequestBody LoginRequest req) {
        try {
            logging.info("login method authentication");
            logging.debug("authenticating user");
            TokenDTO accessToken = authService.login(req);
            return ResponseEntity.ok(accessToken);
        } catch (Exception e) {
            logging.error("cannot login user b/c invalid info");
            return new ResponseEntity("Cannot authenticate user", HttpStatus.UNAUTHORIZED);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping()
    public  ResponseEntity<AuthDTO> getCurrentUser() {
        try {
            logging.info("get current user");
            return ResponseEntity.ok(authService.getCurrentUserService());
        } catch (Exception e) {
            logging.error("Internal error cannot get current user");
            return new ResponseEntity("Internal error cannot get current user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
