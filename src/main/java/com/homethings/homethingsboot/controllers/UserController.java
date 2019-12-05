package com.homethings.homethingsboot.controllers;

import com.hazelcast.core.HazelcastInstance;
import com.homethings.homethingsboot.models.Account;
import com.homethings.homethingsboot.models.Profile;
import com.homethings.homethingsboot.repository.ProfileRepository;
import com.homethings.homethingsboot.repository.UserRepository;
import com.homethings.homethingsboot.validation.LoginFormBean;
import com.homethings.homethingsboot.validation.RegistrationFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private ProfileRepository profileRepository;

    private final Logger logger =  LoggerFactory.getLogger(UserController.class);

    private final HazelcastInstance hazelcastInstance;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public UserController(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @PostMapping(path = "/login")
    public ResponseEntity processLogin(
        HttpSession session,
        @ModelAttribute("formLogin") LoginFormBean form, BindingResult result) {

        Account found = userDAO.findByEmail(form.getEmail());
        if (found == null || !encoder.matches(form.getPassword(), found.getPassword())) {
            session.setAttribute("userId", found.getId());
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }

        return new ResponseEntity<>(
            "NO",
            HttpStatus.BAD_REQUEST);

    }

    @PostMapping(path = "/registration")
    @Transactional
    public ResponseEntity processRegistration(HttpSession session, @ModelAttribute("formRegistration") RegistrationFormBean form, BindingResult result
    ) {
        if (form.getPasswordConfirmation() == null || !form.getPasswordConfirmation().equals(form.getPassword())) {
            result.addError(new FieldError("formRegistration", "passwordConfirmation",
                    "Confirmation doesn't match."));
        }

        if(result.hasErrors()){
            return new ResponseEntity<>(
                    result.toString(),
                    HttpStatus.BAD_REQUEST);
        }

        Account account = new Account(form.getEmail(), encoder.encode(form.getPassword()));
        Profile profile = new Profile();
        profile.setAccount(account);
        try {
            profileRepository.save(profile);
            userDAO.save(account);
            session.setAttribute("userId", account.getId());
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (NoResultException notFound) {
            return new ResponseEntity<>(
                    "NO",
                    HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/logout")
    @Transactional
    public ResponseEntity processLogout(HttpSession session) {
        try {
            session.removeAttribute("userId");
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (NoResultException notFound) {
            return new ResponseEntity<>(
                    "NO",
                    HttpStatus.BAD_REQUEST);
        }
    }

}