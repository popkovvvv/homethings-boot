package com.homethings.homethingsboot.service;

import com.homethings.homethingsboot.models.Account;
import com.homethings.homethingsboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserRolesService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account found = userRepository.findByLogin(login);
        if (found == null) {
            throw new UsernameNotFoundException("Account " + login + " not found.");
        }

        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (found.getRole().equals(Account.AccessRole.ADMIN)) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return new User(login, found.getPassword(), roles);
    }
}