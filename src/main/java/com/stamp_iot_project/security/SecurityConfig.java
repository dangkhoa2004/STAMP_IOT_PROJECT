package com.stamp_iot_project.security;

// import com.stamp_iot_project.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//     private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Sử dụng NoOpPasswordEncoder: mật khẩu không được mã hoá (không an toàn cho production)
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authenticationProvider(authenticationProvider())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/thietbi/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                .requestMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/webjars/**").permitAll()
                .anyRequest().permitAll())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
