package com.homethings.homethingsboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.homethings.homethingsboot.models.Home;

@Repository
public interface HomeRepository extends PagingAndSortingRepository<Home, Long> {

    Home findById(long id);

    Home findByTitle(String title);

    Home findByTitleIsLike(String title);

}
