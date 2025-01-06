package com.tec.billing.DirectDebit.repository;

import com.tec.billing.DirectDebit.entity.OutboundMandateInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboundMandateInformationRepository extends JpaRepository<OutboundMandateInformation,Long> {

    @Query("SELECT omi FROM OutboundMandateInformation omi where ddiStatus = 'INACTIVE' and fileYn <> 'Y'")
    List<OutboundMandateInformation> loadOmiForInactiveAndFileAsN();
}
