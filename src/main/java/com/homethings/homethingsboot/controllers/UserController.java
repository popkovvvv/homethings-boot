package com.homethings.homethingsboot.controllers;

import com.homethings.homethingsboot.models.User;
import com.homethings.homethingsboot.repository.UserRepository;
import com.homethings.homethingsboot.validation.LoginFormBean;
import com.homethings.homethingsboot.validation.RegistrationFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping(path = "/login")
    public ResponseEntity processLogin(
            HttpSession session,
            @ModelAttribute("formLogin") LoginFormBean form, BindingResult result) {
        try {
            User found = userDAO.findByEmailAndPassword(form.getEmail(), form.getPassword());
            session.setAttribute("userId", found.getId());
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (NoResultException notFound) {
            return new ResponseEntity<>(
                    "NO",
                    HttpStatus.BAD_REQUEST);
        }
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

        User user = new User(form.getEmail(), form.getPassword());

        try {
            userDAO.save(user);
            session.setAttribute("userId",user.getId());
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