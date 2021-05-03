package com.corona.insights.controller;

import com.corona.insights.scheduler.FileProcessScheduler;
import com.corona.insights.scheduler.GitScheduler;
import com.corona.insights.scheduler.WebCrawlScheduler;
import com.corona.insights.service.CoronaDataEnrichmentService;
import com.corona.insights.service.CoronaETLProcessingService;
import com.corona.insights.service.CoronaHardestHitService;
import com.corona.insights.service.CoronaVaccinationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/corona/vaccination")
@Slf4j
@AllArgsConstructor
public class VaccinationInfo {

        private final CoronaVaccinationService coronaVaccinationService;

        @RequestMapping(value="/registerPin" , method= RequestMethod.GET)
        public ResponseEntity registerPinCode(@RequestParam int pinCode) {
            boolean result = coronaVaccinationService.registerPinCode(pinCode);
            return ResponseEntity.ok().body(result);
        }

        @RequestMapping(value="/registerDistrictId" , method= RequestMethod.GET)
        public ResponseEntity registerDistrict(@RequestParam int districtId) {
            boolean result = coronaVaccinationService.registerDistrictId(districtId);
            return ResponseEntity.ok().body(result);
        }

}
