package com.tec.billing.DirectDebit.repository;

import com.tec.billing.DirectDebit.entity.ReturnedDebitItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnedDebitItemRepository extends JpaRepository<ReturnedDebitItem, Long> {

    @Query("SELECT rdi FROM ReturnedDebitItem rdi where rdi.notifiedYn <> 'Y' ")
    List<ReturnedDebitItem> getAllUnNotifiedYn();

}

