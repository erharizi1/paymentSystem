package com.abp.paymentSystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.abp.paymentSystem.entity.Finance;

@Repository
public interface FinanceRepository extends CrudRepository<Finance, Long> {

}
