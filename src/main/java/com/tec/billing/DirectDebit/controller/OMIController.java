package com.tec.billing.DirectDebit.controller;

import com.tec.billing.DirectDebit.service.OMIServiceARUDD;
import com.tec.billing.DirectDebit.service.OMIServiceAUDDIS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
public class OMIController {

    @Autowired
    OMIServiceAUDDIS omiServiceAUDDIS;

    @Autowired
    OMIServiceARUDD omiServiceARUDD;

    @RequestMapping("/health-check")
    public String healthCheck(){
        return "Health is OK";
    }

    @RequestMapping("/PRE-25")
    public String interfaceAUDDIS() throws JAXBException, IOException {
        omiServiceAUDDIS.auddis();
        return null;
    }

    @RequestMapping("/PRE-11")
    public String interfaceARUDD() throws IOException {
        omiServiceARUDD.arudd();
        return null;
    }
}
