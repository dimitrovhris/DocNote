package com.app.docnote.service.impl;

import com.app.docnote.repository.UserRepository;
import com.app.docnote.model.entity.UserEntity;
import com.app.docnote.model.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DocNoteUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public DocNoteUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
        return userRepository.findFirstByUsername(emailOrUsername)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + emailOrUsername + " not found!"));

    }
    private UserDetails map(UserEntity user){
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(DocNoteUserDetailsService::map).toList())
                .build();
    }
    private static GrantedAuthority map(UserRole userRole){
        return new SimpleGrantedAuthority("ROLE_" + userRole.getRole().name());
    }

}