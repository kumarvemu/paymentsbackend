package com.allstate.payments.service;

import com.allstate.payments.data.UserRepository;
import com.allstate.payments.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        user.setPassword( bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}

