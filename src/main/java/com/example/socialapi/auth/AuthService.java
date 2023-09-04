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
    private final MyAuthProvider myAuthProvider;
    private final UserDTOMapper mapper;
    private final AuthDTOMapper authDTOMapper;

    public TokenDTO register(RegisterRequest req) {
        User user = new User(req.getName(),
                req.getGmail(),
                passwordEncoder.encode(req.getPassword()),
                req.getGender(),
                req.getAvatarURL(),
                LocalDateTime.now());
        repository.save(user);
        String token = jwtService.tokenGenerator(user);

        return new TokenDTO(token);
    }

    public TokenDTO login(LoginRequest req) {
        // if gmail and pass correct // check before sending
        authenticate(req.getGmail().toString(), req.getPassword().toString());
        User user = repository.findUsersByGmail(req.getGmail()).get();
        String token = jwtService.tokenGenerator(user);
        return new TokenDTO(token);
    }

    private void authenticate(String gmail, String password) {
        myAuthProvider.authenticate(new UsernamePasswordAuthenticationToken(
                gmail, password
        ));
    }

    public AuthDTO getCurrentUserService() {
        /* Solution */
        /* https://stackoverflow.com/questions/32052076/how-to-get-the-current-logged-in-user-object-from-spring-security */
        return authDTOMapper.apply((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        return mapper.apply((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
