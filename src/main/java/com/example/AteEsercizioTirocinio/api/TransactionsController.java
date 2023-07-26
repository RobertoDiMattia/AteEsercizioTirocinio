package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;



}
