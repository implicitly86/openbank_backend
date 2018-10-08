/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.configuration;

import com.implicitly.security.JwtAuthenticationEntryPoint;
import com.implicitly.security.JwtAuthenticationFilter;
import com.implicitly.service.impl.CustomUserDetailsService;
import com.implicitly.utils.filter.CustomCorsFilter;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Конфигурация безопасности приложения.
 *
 * @author Emil Murzakaev.
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Тег используемый при логировании.
     */
    private static final String LOG_TAG = "[SECURITY_CONFIGURATION] ::";

    /**
     * {@link CustomUserDetailsService}
     */
    private final CustomUserDetailsService userDetailsService;

    /**
     * {@link JwtAuthenticationEntryPoint}
     */
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    /**
     * Конструктор.
     *
     * @param userDetailsService {@link CustomUserDetailsService}.
     */
    @Autowired
    public SecurityConfiguration(CustomUserDetailsService userDetailsService,
            JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    /**
     * {@link PostConstruct}
     */
    @PostConstruct
    public void init() {
        if (log.isInfoEnabled()) {
            log.info(
                    "{} has been initialized.",
                    LOG_TAG
            );
        }
    }

    /**
     * {@link WebSecurityConfigurerAdapter#configure(AuthenticationManagerBuilder)}
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * {@link WebSecurityConfigurerAdapter#configure(HttpSecurity)}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**")
                        .permitAll()
                    .antMatchers(
                            "/login",
                            "/**/api-docs",
                            "/swagger**",
                            "/swagger-resources/**",
                            "/**/*.png",
                            "/**/*.gif",
                            "/**/*.svg",
                            "/**/*.jpg",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js"
                    )
                        .permitAll()
                    /*
                    .antMatchers("/admin/**")
                        .hasAuthority("admin")
                    */
                    .anyRequest()
                        .authenticated();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new CustomCorsFilter(), JwtAuthenticationFilter.class);
    }

    /**
     * {@link WebSecurityConfigurerAdapter#authenticationManagerBean()}
     */
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * {@link JwtAuthenticationFilter}
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

}
