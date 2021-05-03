package com.corona.insights.service;

import com.corona.insights.model.Center;
import com.corona.insights.model.Session;
import com.corona.insights.model.VaccinationInfo;
import com.corona.insights.task.NotifyVaccinationDetailsByDistrict;
import com.corona.insights.task.NotifyVaccinationDetailsByPin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class CoronaVaccinationService {

    private final RestTemplate restTemplate;
    private final SpeechService speechService;
    private final Set<Integer> requestedPinCodes;
    private final Set<Integer> requestedDistrictIds;

    public CoronaVaccinationService(RestTemplate restTemplate, SpeechService speechService) {
        this.restTemplate = restTemplate;
        this.speechService = speechService;
        this.requestedPinCodes = new HashSet<>();
        this.requestedDistrictIds = new HashSet<>();
    }

    public boolean registerPinCode(Integer pinCode) {
        log.info("registered a new pin code = {}", pinCode);
        return requestedPinCodes.add(pinCode);
    }

    public boolean registerDistrictId(Integer districtId) {
        log.info("registered a new district id = {}", districtId);
        return requestedDistrictIds.add(districtId);
    }

    @Scheduled(fixedRate = 10000)
    public void getVaccinationInfo() {
        int age = 18;
        requestedPinCodes.stream().parallel().forEach(pinCode -> new NotifyVaccinationDetailsByPin(restTemplate,speechService,pinCode).NotifyActiveVaccinationCentresWithMinAge(age));
        requestedDistrictIds.stream().parallel().forEach(districtId -> new NotifyVaccinationDetailsByDistrict(restTemplate,speechService, districtId).NotifyActiveVaccinationCentresWithMinAge(age));
    }

}
