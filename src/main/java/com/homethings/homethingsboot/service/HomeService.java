package com.homethings.homethingsboot.service;

import com.homethings.homethingsboot.dao.HomeDAO;
import com.homethings.homethingsboot.models.Home;
import com.homethings.homethingsboot.models.pojo.HomePojo;
import com.homethings.homethingsboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HomeService {

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

    public HomePojo getHome(long homeId){
        Home home = homeDAO.findById(homeId);
        HomePojo homePojo = new HomePojo();

        homePojo.setId(home.getId());
        homePojo.setTitle(home.getTitle());
        homePojo.setCreator(home.getCreator());
        homePojo.setNoteList(home.getNoteList());
        homePojo.setPaymentList(home.getPaymentList());
        homePojo.setTaskList(home.getTaskList());
        homePojo.setUsers(home.getUsers());
        homePojo.setDate(home.getDate());

        return homePojo;
    }




}
