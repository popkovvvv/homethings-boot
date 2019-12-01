package com.homethings.homethingsboot.repository;

import com.homethings.homethingsboot.models.Profile;
import com.homethings.homethingsboot.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {

    Profile findByUser(User user);

    List<Profile> findAllByName(String name);

    List<Profile> findAllBySurname(String surname);

}
