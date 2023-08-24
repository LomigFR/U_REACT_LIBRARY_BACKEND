package com.luv2code.springbootlibrary.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

/**
 * @author Guillaume COLLET
 */
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Disable Cross Site Request Forgery
        http.csrf().disable();

        // Protect endpoints at /api/<types>/secure
        http.authorizeRequests(configurer ->
                        configurer
                                .antMatchers("/api/books/secure/**",
                                        "/api/reviews/secure/**")
                                .authenticated())
                .oauth2ResourceServer()
                .jwt();

        // Add CORS filter
        http.cors();

        // Add content negotiation strategy
        http.setSharedObject(
                ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy()
        );

        /**
         * Force Okta to send a non-empty "401 code" for unauthenticated status
         * --> friendly and more readable response
         */
        Okta.configureResourceServer401ResponseBody(http);

        return http.build();
    }
}
