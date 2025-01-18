package com.tec.billing.DirectDebit.controller;

import com.tec.billing.DirectDebit.entity.EntityRegister;
import com.tec.billing.DirectDebit.repository.EntityRegisterRepository;
import com.tec.billing.DirectDebit.service.EntityRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EntityRegisterController {

/*    @Autowired
    EntityRegisterRepository entityRegisterRepository;*/

    @Autowired
    EntityRegisterService entityRegisterService;

    @GetMapping("/ER/health-check")
    public String healthCheck(){
        return "Health from ER is OK";
    }

    @PostMapping("/createER")
    public String createEntityRegister(@RequestBody EntityRegister entityRegister){
        entityRegisterService.createEntityRegisterFromJson(entityRegister);
        return null;
    }

    @PutMapping("/updateER")
    public String updateEntityRegister(@RequestBody EntityRegister entityRegister){
        entityRegisterService.updateEntityRegisterFromXml(entityRegister);
        return null;
    }
}
