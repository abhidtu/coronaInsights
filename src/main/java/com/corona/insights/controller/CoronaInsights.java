package com.corona.insights.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/corona")
@Slf4j
public class CoronaInsights {

    @RequestMapping(value="/insights/" , method= RequestMethod.GET)
    public ResponseEntity getCoronaInsights() {
        log.info("Hello");
        return ResponseEntity.ok().build();
    }

}
