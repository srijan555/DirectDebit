package com.tec.billing.DirectDebit.controller;

import com.tec.billing.DirectDebit.entity.ReturnedDebitItem;
import com.tec.billing.DirectDebit.service.FlatFileReaderService;
import com.tec.billing.DirectDebit.service.OMIServiceARUDD;
import com.tec.billing.DirectDebit.service.OMIServiceAUDDIS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
public class OMIController {

    @Autowired
    OMIServiceAUDDIS omiServiceAUDDIS;

    @Autowired
    OMIServiceARUDD omiServiceARUDD;

    @Autowired
    FlatFileReaderService flatFileReaderService;

    @RequestMapping("/health-check")
    public String healthCheck(){
        return "Health is OK";
    }

    @RequestMapping("/PRE-25")
    public String interfaceAUDDIS() throws JAXBException, IOException {
        omiServiceAUDDIS.auddis();
        return null;
    }

    @GetMapping("/PRE-11")
    public String interfaceARUDD() throws IOException {
        omiServiceARUDD.arudd();
        //List<ReturnedDebitItem> returnedDebitItems = FlatFileReaderService.parseFlatFile("D:/Learning/Spring Boot/DirectDebit/Inbound/ARUDD/ARUDD_07012025_001.csv");
        return null;
    }
}
