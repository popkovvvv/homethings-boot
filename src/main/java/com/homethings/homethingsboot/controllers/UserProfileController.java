package com.homethings.homethingsboot.controllers;

import com.homethings.homethingsboot.models.Profile;
import com.homethings.homethingsboot.models.Account;
import com.homethings.homethingsboot.repository.ProfileRepository;
import com.homethings.homethingsboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

@RestController
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping(path = "/profile/get")
    public Profile getProfileByUser(HttpSession session){
        Account account = userRepository.findById((long) session.getAttribute("userId"));
        return profileRepository.findByAccount(account);
    }
}
