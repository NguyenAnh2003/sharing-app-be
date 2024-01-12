package com.example.socialapi.auth;

import com.example.socialapi.auth.dto.AuthDTO;
import com.example.socialapi.auth.dto.AuthDTOMapper;
import com.example.socialapi.auth.dto.TokenDTO;
import com.example.socialapi.auth.requests.LoginRequest;
import com.example.socialapi.auth.requests.RegisterRequest;
import com.example.socialapi.common.exception.errors.NotFoundException;
import com.example.socialapi.config.JwtService;
import com.example.socialapi.config.MyAuthProvider;
import com.example.socialapi.user.User;
import com.example.socialapi.user.UserRepository;
import com.example.socialapi.user.dto.UserDTO;
import com.example.socialapi.user.dto.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
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
    private final MyAuthProvider myAuthProvider;
    private final UserDTOMapper mapper;
    private final AuthDTOMapper authDTOMapper;
    private static final Logger logging = LoggerFactory.getLogger(AuthService.class);

    public UserDTO register(String name, String gmail, String password,
                            String avatarURL) {
        try {
            logging.info("register method service class");
            User user = new User(name, gmail, passwordEncoder.encode(password),
                    avatarURL, LocalDateTime.now());
            User newUser = repository.save(user);
            return mapper.apply(newUser);
        } catch (Exception e) {
            logging.error("Internal error cannot register");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public TokenDTO login(String gmail, String password) {
        try {
            logging.info("login method");
            logging.debug("authenticating user");
            authenticate(gmail, password); // authenticate with MyAuthProvider
            User user = repository.findUsersByGmail(gmail).get();
            String token = jwtService.tokenGenerator(user);
            return new TokenDTO(token);
        } catch (Exception e) {
            logging.error("Internal error cannot authenticate");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    private void authenticate(String gmail, String password) {
        try {
            logging.info("authenticate function auth service");
            logging.debug("authenticating with auth provider");
            myAuthProvider.authenticate(new UsernamePasswordAuthenticationToken(
                    gmail, password
            ));
        } catch (Exception e) {
            logging.error("Internal error cannot authenticate");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }

    public AuthDTO getCurrentUserService() {
        try {
            logging.info("get signed in user");
            return authDTOMapper.apply((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        } catch (Exception e) {
            logging.error("Internal error cannot get current user");
            throw new RuntimeException("Message " + e.getMessage() + " Cause " + e.getCause());
        }
    }
}
