package org.example.bookstorespringbootsecurity.config;

import org.example.bookstorespringbootsecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthService authService;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/register", "/login")
//                        .permitAll()
//                        .requestMatchers("/save")
//                        .authenticated()
//                )
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/create")
//                        .permitAll()
//                )
//                .userDetailsService(authService);
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequestConfigurer) -> {
                    authorizeHttpRequestConfigurer
                            .requestMatchers("/register", "/login").permitAll()
                            .requestMatchers("/admin/all-user","/admin/all-books").hasRole("ADMIN")
                            .requestMatchers("/book/create-book").hasRole("SELLER")
                            .requestMatchers("/order/**").hasRole("USER")
                            .anyRequest().authenticated();
                })
                .formLogin((formLogin -> {
                    formLogin
//                            .loginPage("/user/auth")
                            .defaultSuccessUrl("/alone/home");
                }))
                .userDetailsService(authService)
                .build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
