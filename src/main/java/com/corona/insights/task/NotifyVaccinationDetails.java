package com.corona.insights.task;

import com.corona.insights.model.Center;
import com.corona.insights.model.Session;
import com.corona.insights.model.VaccinationInfo;
import com.corona.insights.service.SpeechService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public abstract class NotifyVaccinationDetails {

    private final RestTemplate restTemplate;
    private final SpeechService speechService;

    public NotifyVaccinationDetails(RestTemplate restTemplate, SpeechService speechService) {
        this.restTemplate = restTemplate;
        this.speechService = speechService;
    }

    protected abstract String getBaseURLWithParams();

    protected abstract String getType();

    public abstract void NotifyActiveVaccinationCentresWithMinAge(int minAge);

    protected void process(Integer data , int minAge) {
        List<Center> centers = null;
        log.info("Looking for eligible vaccination centres at {} = {}", getType(), data);
        ResponseEntity<VaccinationInfo> vaccinationInfoResponse = restTemplate.getForEntity(getBaseURLWithParams(), VaccinationInfo.class);
        if(vaccinationInfoResponse.getBody() != null) {
            centers = vaccinationInfoResponse.getBody().getCenters();
            log.info("Found {} vaccination centres at {} = {}", centers.size(), getType(), data);
        }
        if(centers != null) {
            int count = 0;
            for (Center center : centers) {
                List<Session> sessions = center.getSessions();
                for (Session session : sessions) {
                    if(session.getMinAgeLimit() == minAge && session.getAvailableCapacity() > 0) {
                        count++;
                        log.info("Vaccine for {} available at center {}, pin code = {} on {}, quantity = {}",session.getMinAgeLimit(), center.getName(), center.getPincode(), session.getDate(), session.getAvailableCapacity());
                        //speechService.speak("Vaccine for "+ session.getMinAgeLimit() +" available at center "+ center.getName() + "pin code "+ center.getPincode() +" on date    "+ session.getDate() +", quantity  "+session.getAvailableCapacity());
                    }
                }
            }
            if (count > 0) {
                //speechService.speak("found total "+count+" active vaccine centres for age "+minAge+" for "+ getType() + " = " + data);
                log.info("found total {} active vaccine centres for age {} for {} = {}", count, minAge, getType(), data);
            }else {
                log.info("Sorry no vaccine available for  age {}", minAge);
            }
            log.info("Search complete for {} = {}\n\n", getType(), data);
        }else {
            log.info("No Centre found for given {}", getType());
        }
    }

}