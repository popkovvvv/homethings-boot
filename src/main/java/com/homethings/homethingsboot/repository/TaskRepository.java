package com.homethings.homethingsboot.repository;

import com.homethings.homethingsboot.models.Task;
import com.homethings.homethingsboot.models.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    Task findById(long id);

    Task findTaskByTitleIsLike(String title);

    Task findBySender(Account account);

    Task findBySenderOrPerformed(Account sender, Account performed);

    List<Task> getAllByHomeId(long homeId);

}
