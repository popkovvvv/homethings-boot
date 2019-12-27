package com.homethings.config.security;

import com.homethings.models.Account;
import com.homethings.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Component
public class UserRolesService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        // Let people login with either username or email
        Account user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Account " + login + " not found.");
        }
        return UserPrincipal.create(user);
    }

}