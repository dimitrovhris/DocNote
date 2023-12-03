package com.app.docnote.config;


import com.app.docnote.filter.LoginPageFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@SuppressWarnings("removal")
public class SecurityConfiguration {

    @Bean

    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                .addFilterBefore(new LoginPageFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        //Define which URLs are visible for users
                        authorizeRequests -> authorizeRequests
                                //All static resources which are situated in js, images, css are available for anyone
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                //Allow anyone to see the home page, register page and login page
                                .requestMatchers( "/", "/index", "/user/register", "/user/login", "/user/login-error").permitAll()
                                .requestMatchers("/manage-website").hasRole("ADMIN")

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