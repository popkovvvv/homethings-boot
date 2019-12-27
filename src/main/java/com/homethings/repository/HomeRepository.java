package com.homethings.repository;

import com.homethings.models.Home;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends PagingAndSortingRepository<Home, Long> {

    Home findById(long id);

    Home findByTitle(String title);

    Home findByTitleIsLike(String title);

}
