package com.app.docnote.config;


import com.app.docnote.repository.UserRepository;
import com.app.docnote.service.impl.DocNoteUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@SuppressWarnings("removal")
public class SecurityConfiguration {

    @Bean

    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

//                .addFilterBefore(new LoginPageFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers( "/", "/index", "/user/register", "/user/login", "/user/login-error", "/user/register/passwords-do-not-match").permitAll()
                                .requestMatchers("/manage-website", "/manage-website/admins", "/manage-website/waiting-registrations", "manage-website/doctors").hasRole("ADMIN")
                                .anyRequest().authenticated()
                ).formLogin(
                        formLogin ->{
                            formLogin
                                    .loginPage("/user/login")
                                    .usernameParameter("emailOrUsername")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/")
                                    .failureUrl("/user/login-error");
                        }
                ).logout(
                        logout -> {
                            logout.logoutUrl("/user/logout")
                                    .logoutSuccessUrl("/")
                                    .invalidateHttpSession(true);
                        })
                .csrf().disable()
                ;
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