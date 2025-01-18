package com.tec.billing.DirectDebit.repository;

import com.tec.billing.DirectDebit.entity.EntityRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EntityRegisterRepository extends JpaRepository<EntityRegister,Long> {

    @Query("SELECT er FROM EntityRegister er WHERE er.createdOn <= :currentDate and er.isMandateCreatedYN is NULL")
    List<EntityRegister> findEntityRegisterBeforeBusinessDate(LocalDateTime currentDate);

    EntityRegister findByGroupNo(String groupNo);
}
