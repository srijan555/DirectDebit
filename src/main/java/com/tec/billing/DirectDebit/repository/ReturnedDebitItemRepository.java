package com.tec.billing.DirectDebit.repository;

import com.tec.billing.DirectDebit.entity.ReturnedDebitItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnedDebitItemRepository extends JpaRepository<ReturnedDebitItem, Long> {
}

