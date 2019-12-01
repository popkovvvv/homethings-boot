package com.homethings.homethingsboot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.homethings.homethingsboot.models.Payment;
import com.homethings.homethingsboot.models.User;

import java.util.List;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {

    Payment findById(long id);

    Payment findBySender(User sender);

    List<Payment> getAllByHomeId(long homeId);


}
