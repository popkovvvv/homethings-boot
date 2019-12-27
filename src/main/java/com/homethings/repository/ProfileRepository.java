package com.homethings.repository;

import com.homethings.models.Account;
import com.homethings.models.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {

    Profile findByAccount(Account account);

    List<Profile> findAllByName(String name);

    List<Profile> findAllBySurname(String surname);

}
