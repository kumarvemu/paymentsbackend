package com.allstate.payments.service;

import com.allstate.payments.data.UserRepository;
import com.allstate.payments.domain.User;
import com.allstate.payments.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BootstrapService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserManagementService userManagementService;

    @PostConstruct
    public void createInitialUsers () {
        if (userRepository.findAll().size() == 0) {
            User user1 = new User("user1", "thisisabadpasword", "Matt", UserRole.USER);
            User user2 = new User("user2", "thisisabadpasword", "Sally", UserRole.MANAGER);
            userManagementService.save(user1);
            userManagementService.save(user2);
        }
    }
}

