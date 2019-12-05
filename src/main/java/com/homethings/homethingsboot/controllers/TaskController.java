package com.homethings.homethingsboot.controllers;

import com.homethings.homethingsboot.models.Account;
import com.homethings.homethingsboot.models.Home;
import com.homethings.homethingsboot.models.Task;
import com.homethings.homethingsboot.repository.HomeRepository;
import com.homethings.homethingsboot.repository.TaskRepository;
import com.homethings.homethingsboot.repository.UserRepository;
import com.homethings.homethingsboot.validation.CreateTaskFormBean;
import com.homethings.homethingsboot.validation.EditTaskFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskDao;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private HomeRepository homeDAO;

    @PostMapping(path = "/task")
    @Transactional
    public Task getTask(
            HttpSession session, @RequestParam String id) {

        long taskId = Long.parseLong(id);
        Task task;
        try {
            task = taskDao.findById(taskId);
        } catch (Exception e){
            throw e;
        }
        return task;
    }

    @PostMapping(path = "/task/create")
    @Transactional
    public Task createTask(
            HttpSession session,
            @ModelAttribute("createTaskForm") CreateTaskFormBean form,
            BindingResult result) {

        long homeId = (long) session.getAttribute("homeId");
        long userId = (long) session.getAttribute("userId");

        Account provider = userDao.findById(userId);
        Account performed = userDao.findById(form.getPerformed());
        Home home = homeDAO.findById(homeId);

        Task task = new Task(form.getTitle(),provider,performed);
        task.setChecked(false);
        task.setDate(new Date());
        task.setHome(home);
        taskDao.save(task);

        return task;
    }

    @PostMapping(path = "/task/delete")
    @Transactional
    public ResponseEntity deleteTask(@RequestParam String id, HttpSession session) {

        long taskId = Long.parseLong(id);
        long homeId = (long) session.getAttribute("homeId");

        try {
            Task task = taskDao.findById(taskId);
            Home home = homeDAO.findById(homeId);
            home.getTaskList().remove(task);
            homeDAO.save(home);
            taskDao.delete(task);
            return new ResponseEntity<>(
                    "Task " +task.getId()+ " deleted",
                    HttpStatus.OK);
        } catch (Exception entity) {
            return new ResponseEntity<>(
                    "IS dont correct",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/task/update")
    @Transactional
    public ResponseEntity updateTask(
            @ModelAttribute("editTaskForm") EditTaskFormBean formEdit,
            @RequestParam String taskId) {

        long id = Long.parseLong(taskId);
        Task task = taskDao.findById(id);
        Account performed = userDao.findById(formEdit.getPerformed());

        task.setTitle(formEdit.getTitle());
        task.setChecked(formEdit.isChecked());
        task.setDate(new Date());
        task.setPerformed(performed);

        try {
            taskDao.save(task);
            return new ResponseEntity<>(
                    "Task " +task.getId()+ " updated",
                    HttpStatus.OK);
        } catch (Exception entity) {
            return new ResponseEntity<>(
                    "ID don't correct",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
