/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Реализация {@link AuthenticationEntryPoint}.
 *
 * @author Emil Murzakaev.
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * {@link AuthenticationEntryPoint#commence(HttpServletRequest, HttpServletResponse, AuthenticationException)}
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        log.info("unauthorized request : {} {}", request.getMethod(), request.getRequestURL());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not authorized");
    }

}
