package com.homethings.homethingsboot.repository;

import com.homethings.homethingsboot.models.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Account, Long> {

    Account findById(long id);

    Account findByEmail(String email);

    Account findByRole(Account.AccessRole role);

    Account findByEmailAndPassword(String email, String password);

    List<Account> getAllByHomeId(long homeId);




}
