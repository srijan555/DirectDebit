package com.tec.billing.DirectDebit.service;

import com.tec.billing.DirectDebit.entity.EntityRegister;
import com.tec.billing.DirectDebit.repository.EntityRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class EntityRegisterService {

    @Autowired
    EntityRegisterRepository entityRegisterRepository;

    public void createEntityRegisterFromJson(EntityRegister entityRegister){
        entityRegister.setCreatedBy("ADMIN");
        entityRegister.setCreatedOn(Date.valueOf(LocalDate.now()));
        entityRegisterRepository.save(entityRegister);
    }

    public ResponseEntity<String> updateEntityRegisterFromXml(EntityRegister entityRegister){
        String groupNo=entityRegister.getGroupNo();
        EntityRegister byGroupNo = entityRegisterRepository.findByGroupNo(groupNo);
        byGroupNo.setGroupName(entityRegister.getGroupName());
        if(byGroupNo!=null){
            entityRegisterRepository.save(byGroupNo);
            return new ResponseEntity<>("group updated", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("group does not exist",HttpStatus.NOT_FOUND);
        }
    }
}
/*
{
        "groupNo": "999995",
        "groupName": "Temp group name",
        "groupEffectiveDate": "2025-01-18T00:00:00-05:00",
        "accountNo": "87654320",
        "accountName": "Temp account name",
        "sortCode": "631986",
        "isMandateCreatedYN": "N"
}*/

/*<entityRegister>
    <groupNo>999996</groupNo>
    <groupName>Newer Temp group name</groupName>
    <groupEffectiveDate>2025-01-18T00:00:00-05:00</groupEffectiveDate>
    <accountNo>87654320</accountNo>
    <accountName>Newer Temp account name</accountName>
    <sortCode>631986</sortCode>
    <isMandateCreatedYN>N</isMandateCreatedYN>
</entityRegister>*/

