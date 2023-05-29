package com.example.newsfeedapi.auth;

import com.example.newsfeedapi.auth.requests.LoginRequest;
import com.example.newsfeedapi.auth.requests.RegisterRequest;
import com.example.newsfeedapi.config.JwtService;
import com.example.newsfeedapi.user.User;
import com.example.newsfeedapi.user.UserRepository;
import com.example.newsfeedapi.user.dto.UserDTO;
import com.example.newsfeedapi.user.dto.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserDTOMapper mapper;

    public AuthResponse register(RegisterRequest req) {
        User user = new User(req.getName(),
                req.getGmail(),
                passwordEncoder.encode(req.getPassword()),
                req.getGender(),
                req.getAvatarURL(),
                LocalDateTime.now());
        repository.save(user);
        String token = jwtService.tokenGenerator(user);

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest req) {
        // if gmail and pass correct
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getGmail(),
                        req.getPassword()
                )
        );

        User user = repository.findUsersByGmail(req.getGmail())
                .orElseThrow();
        String token = jwtService.tokenGenerator(user);

        return new AuthResponse(token);
    }

    public UserDTO getCurrentUserService() {
        /* Solution */
        /* https://stackoverflow.com/questions/32052076/how-to-get-the-current-logged-in-user-object-from-spring-security */
        return mapper.apply((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
