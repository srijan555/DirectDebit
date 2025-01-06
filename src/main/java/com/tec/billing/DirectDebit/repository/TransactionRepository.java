package com.tec.billing.DirectDebit.repository;

import com.tec.billing.DirectDebit.entity.EntityRegister;
import com.tec.billing.DirectDebit.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
