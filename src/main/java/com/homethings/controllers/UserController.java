package com.homethings.controllers;

import com.hazelcast.core.HazelcastInstance;
import com.homethings.config.security.UserRolesService;
import com.homethings.models.Account;
import com.homethings.models.Profile;
import com.homethings.repository.ProfileRepository;
import com.homethings.repository.UserRepository;
import com.homethings.validation.LoginFormBean;
import com.homethings.validation.RegistrationFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRolesService userRolesService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final HazelcastInstance hazelcastInstance;


    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public UserController(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping(path = "/login")
    public ResponseEntity processLogin(
            HttpSession session,
            @ModelAttribute("formLogin") LoginFormBean form) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        form.getLogin(),
                        form.getPassword()
                )
        );

        try {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e){
            return new ResponseEntity<>(
                    "NO",
                    HttpStatus.UNAUTHORIZED);
        }

//        Account found = userDAO.findByEmail(form.getLogin());
//        if (found == null || !encoder.matches(form.getPassword(), found.getPassword())) {
//            return new ResponseEntity<>(
//                    "NO",
//                    HttpStatus.UNAUTHORIZED);
//        }

        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
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

        Account account = new Account(form.getLogin(), encoder.encode(form.getPassword()),form.getEmail());
        account.setLogin(form.getLogin());
        Profile profile = new Profile();
        profile.setAccount(account);
        account.setProfile(profile);
        try {
            profileRepository.save(profile);
            userDAO.save(account);
            session.setAttribute("userId", account.getId());
            return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
        } catch (NoResultException notFound) {
            return new ResponseEntity<>(
                    "NO",
                    HttpStatus.BAD_REQUEST);
        }

    }

//    @GetMapping(path = "/logout")
//    @Transactional
//    public ResponseEntity processLogout(HttpSession session, UserDetails userDetails) {
//        try {
//            session.removeAttribute("userId");
//
//            return new ResponseEntity<>("OK", HttpStatus.OK);
//        } catch (NoResultException notFound) {
//            return new ResponseEntity<>(
//                    "NO",
//                    HttpStatus.BAD_REQUEST);
//        }
//    }

}