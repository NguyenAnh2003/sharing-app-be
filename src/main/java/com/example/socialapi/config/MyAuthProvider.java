package com.example.socialapi.config;

import com.example.socialapi.common.exception.errors.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyAuthProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logging = LoggerFactory.getLogger(MyAuthProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logging.info("authentication my auth provider");
        String gmail = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        UserDetails user = userDetailsService.loadUserByUsername(gmail);
        if(passwordEncoder.matches(password, user.getPassword()))
            return new UsernamePasswordAuthenticationToken(user,
                    null, null);
        else {
            logging.error("invalid gmail or password");
            throw new UnauthorizedException("Invalid gmail or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
