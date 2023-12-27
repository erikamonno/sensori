package com.example.sensor.services.impl;

import com.example.sensor.repositories.UtenteRepository;
import com.example.sensor.utils.RoleConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utenteRepository.findByUsername(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getAdmin() ? RoleConstants.ADMIN : RoleConstants.USER)
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
    }

}
