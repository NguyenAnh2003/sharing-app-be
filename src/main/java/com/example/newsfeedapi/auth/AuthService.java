package com.example.newsfeedapi.auth;

import com.example.newsfeedapi.config.JwtService;
import com.example.newsfeedapi.user.User;
import com.example.newsfeedapi.user.UserRepository;
import com.example.newsfeedapi.user.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    public AuthenticationResponse register(RegisterRequest req) {
        User user = new User(req.getName(),
                req.getGmail(),
                passwordEncoder.encode(req.getPassword()),
                req.getGender(),
                req.getAvatarURL(),
                LocalDateTime.now());
        repository.save(user);
        String token = jwtService.tokenGenerator(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthRequest req) {
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
        return new AuthenticationResponse(token);
    }
}
