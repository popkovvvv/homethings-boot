package com.homethings.homethingsboot.controllers;

import com.homethings.homethingsboot.models.*;
import com.homethings.homethingsboot.repository.*;
import com.homethings.homethingsboot.validation.CreateHomeFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.Date;
import javax.persistence.NoResultException;
import java.util.List;


@RestController
public class HomeController {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private HomeRepository homeDAO;

    @Autowired
    private TaskRepository taskDao;

    @Autowired
    private NoteRepository noteDAO;

    @Autowired
    private PaymentRepository paymentDAO;


    @PostMapping(path = "/home/login")
    public ResponseEntity processLogin(
            HttpSession session,
            @RequestParam String title) {

        long accountId = (long) session.getAttribute("userId");
        User user = userDao.findById(accountId);
        Home home;
        try {
            home = homeDAO.findByTitle(title);
        } catch (NoResultException notFound) {
            return new ResponseEntity<>(
                    "create Home!",
                    HttpStatus.BAD_REQUEST);
        }
        user.setHome(home);
        userDao.save(user);
        session.setAttribute("homeId", home.getId());
        return new ResponseEntity<>("you in!", HttpStatus.OK);
    }

    @PostMapping(path = "/home/create")
    @Transactional
    public ResponseEntity createHome(
            HttpSession session,
            @ModelAttribute("createHomeForm") CreateHomeFormBean form,
            BindingResult result) {

        User user = userDao.findById((long) session.getAttribute("userId"));
        user.setRole(User.AccessRole.ADMIN);
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    result,
                    HttpStatus.BAD_REQUEST);
        }
        Home home = new Home(form.getTitle(),user, new Date());
        user.setHome(home);
        userDao.save(user);

        try {
            userDao.save(user);
            session.setAttribute("homeId", home.getId());
            return new ResponseEntity<>("Home created", HttpStatus.OK);
        } catch (NoResultException notFound) {
            return new ResponseEntity<>(
                    "NO",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/home/logout")
    public ResponseEntity processLogout(HttpSession session) {
        try {
            session.removeAttribute("homeId");
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (NoResultException notFound) {
            return new ResponseEntity<>(
                    "NO",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/home")
    public Home getHome(HttpSession session) {
        long homeId = (long) session.getAttribute("homeId");
        return homeDAO.findById(homeId);
    }

    @GetMapping(path = "/home/user/all")
    public List<User> userList(HttpSession session) {
        long homeId = (long) session.getAttribute("homeId");
        return userDao.getAllByHomeId(homeId);
    }

    @GetMapping(path = "/home/task/all")
    public List<Task> taskList(HttpSession session) {
        long homeId = (long) session.getAttribute("homeId");
        return taskDao.getAllByHomeId(homeId);
    }

    @GetMapping(path = "/home/note/all")
    public List<Note> noteList(HttpSession session) {
        long homeId = (long) session.getAttribute("homeId");
        return noteDAO.getAllByHomeId(homeId);

    }

    @GetMapping(path = "/home/payment/all")
    public List<Payment> paymentList(HttpSession session) {
        long homeId = (long) session.getAttribute("homeId");
        return paymentDAO.getAllByHomeId(homeId);
    }



}
