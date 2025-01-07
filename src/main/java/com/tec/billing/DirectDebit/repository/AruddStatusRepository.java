package com.tec.billing.DirectDebit.repository;

import com.tec.billing.DirectDebit.entity.AruddStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AruddStatusRepository extends JpaRepository<AruddStatus, String> {
}
