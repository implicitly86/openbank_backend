/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.controller.security;

import static org.springframework.http.ResponseEntity.ok;

import com.implicitly.dto.security.CredentialsDTO;
import com.implicitly.dto.security.TokenDTO;
import com.implicitly.security.JwtTokenProvider;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Реализация {@link RestController}, обслуживающая запросы безопасности приложения.
 *
 * @author Emil Murzakaev.
 */
@RestController
public class AuthController {

    /**
     * {@link AuthenticationManager}
     */
    private final AuthenticationManager authenticationManager;
    /**
     * {@link JwtTokenProvider}
     */
    private final JwtTokenProvider tokenProvider;

    /**
     * Конструктор.
     *
     * @param authenticationManager {@link AuthenticationManager}
     * @param tokenProvider {@link JwtTokenProvider}
     */
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    /**
     * Аутентификация пользователя.
     *
     * @param credentials данные пользователя.
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> authenticateUser(@Valid @RequestBody CredentialsDTO credentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ok(TokenDTO.builder().accessToken(jwt).build());
    }

}
