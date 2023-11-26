package com.example.docnote.config;


import com.example.docnote.repository.UserRepository;
import com.example.docnote.service.impl.DocNoteUserDetailsService;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        httpSecurity
         .authorizeHttpRequests(
                //Define which URLs are visible for users
                authorizeRequests -> authorizeRequests
                        //All static resources which are situated in js, images, css are available for anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        //Allow anyone to see the home page, register page and login page
                        .requestMatchers("/", "/user/login", "/user/register", "/user/login-error").permitAll()
                        //All other requests are authenticated
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin ->{
                    formLogin
                            //redirect here when we access something which is not allowed.
                            //also this is the page where we perform login.
                            .loginPage("/user/login")
                            // The names of the input fields (in our case in login.html)
                            .usernameParameter("emailOrUsername")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/home")
                            .failureForwardUrl("/user/login-error");
                }
        ).logout(
                logout -> {
                    logout.logoutUrl("/user/logout")
                            .logoutSuccessUrl("/")
                            .invalidateHttpSession(true);
                })
                .requestCache((cache) -> cache
                        .requestCache(requestCache)
                );
        return httpSecurity.build();
    }
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new DocNoteUserDetailsService(userRepository);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
