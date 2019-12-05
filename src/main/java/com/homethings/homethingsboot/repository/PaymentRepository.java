package com.homethings.homethingsboot.repository;

import com.homethings.homethingsboot.models.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.homethings.homethingsboot.models.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {

    Payment findById(long id);

    Payment findBySender(Account sender);

    List<Payment> getAllByHomeId(long homeId);


}
