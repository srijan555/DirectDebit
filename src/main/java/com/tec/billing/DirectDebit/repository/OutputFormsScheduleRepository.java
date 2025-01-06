package com.tec.billing.DirectDebit.repository;

import com.tec.billing.DirectDebit.entity.OutputFormsSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputFormsScheduleRepository extends JpaRepository<OutputFormsSchedule,Long> {

}
