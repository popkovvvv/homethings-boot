package com.homethings.homethingsboot.repository;

import com.homethings.homethingsboot.models.Task;
import com.homethings.homethingsboot.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    Task findById(long id);

    Task findTaskByTitleIsLike(String title);

    Task findBySender(User user);

    Task findBySenderOrPerformed(User sender, User performed);

    List<Task> getAllByHomeId(long homeId);

}
