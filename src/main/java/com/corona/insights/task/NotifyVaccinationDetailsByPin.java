package com.corona.insights.task;

import com.corona.insights.service.SpeechService;
import org.springframework.web.client.RestTemplate;

public class NotifyVaccinationDetailsByPin extends NotifyVaccinationDetails {

    Integer pinCode;
    String baseURLWithParams;

    public NotifyVaccinationDetailsByPin(RestTemplate restTemplate, SpeechService speechService, Integer pinCode) {
        super(restTemplate, speechService);
        this.pinCode = pinCode;
        this.baseURLWithParams = String.format(getBaseURL(), pinCode, "03-05-2021");
    }

    private String getBaseURL() {
        return "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=%s&date=%s";
    }

    @Override
    protected String getBaseURLWithParams() {
        return baseURLWithParams;
    }

    @Override
    protected String getType() {
        return "PinCode";
    }

    @Override
    public void NotifyActiveVaccinationCentresWithMinAge(int minAge) {
        process(pinCode, minAge);
    }

}
