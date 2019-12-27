package com.homethings.repository;

import com.homethings.models.Account;
import com.homethings.models.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {

    Payment findById(long id);

    Payment findBySender(Account sender);

    List<Payment> getAllByHomeId(long homeId);


}
