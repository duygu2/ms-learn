package com.example.orderservice.core.configuration;

import com.turkcell.core.security.BaseSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    private final BaseSecurityService baseSecurityService;

    public SecurityConfiguration(BaseSecurityService baseSecurityService) {
        this.baseSecurityService = baseSecurityService;
    }

    private static final String[] WHITE_LIST={
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        baseSecurityService.configureCommonSecurityRules(httpSecurity);

        httpSecurity

                .authorizeHttpRequests((req)->req.requestMatchers("/api/v1/orders/**").authenticated()
                        .requestMatchers(WHITE_LIST).permitAll());

        //entegrasyonlar yazılacak, white list eklenecek
        return httpSecurity.build();
    }
}
