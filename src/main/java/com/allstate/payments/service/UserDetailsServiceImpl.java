package com.allstate.payments.service;

import com.allstate.payments.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.allstate.payments.domain.User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), roles);
    }
}





