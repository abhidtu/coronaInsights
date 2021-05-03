package com.corona.insights.task;

import com.corona.insights.service.SpeechService;
import org.springframework.web.client.RestTemplate;

public class NotifyVaccinationDetailsByDistrict extends NotifyVaccinationDetails {

    Integer districtId;
    String baseURLWithParams;

    public NotifyVaccinationDetailsByDistrict(RestTemplate restTemplate, SpeechService speechService, Integer districtId) {
        super(restTemplate, speechService);
        this.districtId = districtId;
        this.baseURLWithParams = String.format(getBaseURL(), districtId, "03-05-2021");
    }

    @Override
    protected String getBaseURLWithParams() {
        return baseURLWithParams;
    }

    private String getBaseURL() {
        return "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=%s&date=%s";
    }

    @Override
    protected String getType() {
        return "DistrictId";
    }

    @Override
    public void NotifyActiveVaccinationCentresWithMinAge(int minAge) {
        process(districtId, minAge);
    }

}
