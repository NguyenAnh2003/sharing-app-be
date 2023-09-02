package com.example.socialapi.auth;

import com.example.socialapi.auth.dto.AuthDTO;
import com.example.socialapi.auth.requests.LoginRequest;
import com.example.socialapi.auth.requests.RegisterRequest;
import com.example.socialapi.config.JwtService;
import com.example.socialapi.user.User;
import com.example.socialapi.user.UserRepository;
import com.example.socialapi.user.dto.UserDTO;
import com.example.socialapi.user.dto.UserDTOMapper;
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

    public AuthDTO register(RegisterRequest req) {
        User user = new User(req.getName(),
                req.getGmail(),
                passwordEncoder.encode(req.getPassword()),
                req.getGender(),
                req.getAvatarURL(),
                LocalDateTime.now());
        repository.save(user);
        String token = jwtService.tokenGenerator(user);

        return new AuthDTO(token);
    }

    public AuthDTO login(LoginRequest req) {
        // if gmail and pass correct
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getGmail().toString(),
                        req.getPassword().toString()
                ));

        User user = repository.findUsersByGmail(req.getGmail()).get();
        String token = jwtService.tokenGenerator(user);
        return new AuthDTO(token);
    }

    public UserDTO getCurrentUserService() {
        /* Solution */
        /* https://stackoverflow.com/questions/32052076/how-to-get-the-current-logged-in-user-object-from-spring-security */
        return mapper.apply((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
