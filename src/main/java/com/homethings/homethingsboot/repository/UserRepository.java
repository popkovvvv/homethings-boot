package com.homethings.homethingsboot.repository;

import com.homethings.homethingsboot.models.Home;
import com.homethings.homethingsboot.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findById(long id);

    User findByEmail(String email);

    User findByRole(User.AccessRole role);

    User findByEmailAndPassword(String email, String password);

    List<User> getAllByHomeId(long homeId);




}
