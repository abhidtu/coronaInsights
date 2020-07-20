package com.corona.insights.etl;

import com.corona.insights.dao.CasesDaoImpl;
import com.corona.insights.dao.DistrictWiseDaoImpl;
import com.corona.insights.jooq.corona_insights.enums.DistrictWiseSource;
import com.corona.insights.jooq.corona_insights.tables.pojos.DistrictWise;
import com.corona.insights.jooq.corona_insights.tables.pojos.Location;
import com.corona.insights.jooq.corona_insights.tables.pojos.StateWise;
import com.corona.insights.model.CoronaVirusETLMetricsDTO;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CoronaInsightsDistrictETLProcessor implements ETLProcessor {

    private String country;
    private String state;
    private String district;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Timestamp cutOffDate;
    private CasesDaoImpl casesDao;
    private DistrictWiseDaoImpl districtWiseDao;
    private List<CoronaVirusETLMetricsDTO> coronaVirusETLMetricsDTOList;
    private List<DistrictWise> districtWiseList;

    public CoronaInsightsDistrictETLProcessor(Location location, Timestamp cutOffDate, CasesDaoImpl casesDao, DistrictWiseDaoImpl districtWiseDao) {
        this.state = location.getState();
        this.country = location.getCountry();
        this.district = location.getDistrict();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.districtWiseDao = districtWiseDao;
        this.cutOffDate = cutOffDate;
        this.casesDao = casesDao;
        this.districtWiseDao = districtWiseDao;
        this.districtWiseList = new ArrayList<>();
    }

    @Override
    public void extract() {
        log.info("Step 1: executing Extract for country = {}, state = {}", country, state);
        coronaVirusETLMetricsDTOList = cutOffDate == null ? casesDao.aggregateDataForDestrict(latitude, longitude) : casesDao.aggregateDataForDestrictWithCutOffDate(latitude, longitude, cutOffDate);
    }

    @Override
    public void transform() {
        log.info("Step 2: executing Transform for country = {}, state = {}, district = {}", country, state, district);
        CoronaVirusETLMetricsDTO previousCoronaVirusETLMetricsDTO = new CoronaVirusETLMetricsDTO();
        for (CoronaVirusETLMetricsDTO coronaVirusETLMetricsDTO : coronaVirusETLMetricsDTOList) {
            DistrictWise districtWise = new DistrictWise();
            districtWise.setCountry(country);
            districtWise.setState(state);
            districtWise.setDistrict(district);
            districtWise.setLatitude(latitude);
            districtWise.setLongitude(longitude);

            districtWise.setConfirmed(coronaVirusETLMetricsDTO.getConfirmed());
            districtWise.setDeaths(coronaVirusETLMetricsDTO.getDeaths());
            districtWise.setRecovered(coronaVirusETLMetricsDTO.getRecovered());
            districtWise.setActive(coronaVirusETLMetricsDTO.getConfirmed() - coronaVirusETLMetricsDTO.getDeaths() - coronaVirusETLMetricsDTO.getRecovered());

            coronaVirusETLMetricsDTO.computeDelta(previousCoronaVirusETLMetricsDTO);

            districtWise.setDeltaConfirmed(coronaVirusETLMetricsDTO.getDeltaConfirmed());
            districtWise.setDeltaDeaths(coronaVirusETLMetricsDTO.getDeltaDeaths());
            districtWise.setDeltaRecovered(coronaVirusETLMetricsDTO.getDeltaRecovered());
            districtWise.setDeltaActive(coronaVirusETLMetricsDTO.getDeltaActive());

            districtWise.setReportingDate(coronaVirusETLMetricsDTO.getReportedDate());
            districtWise.setSource(DistrictWiseSource.JHU);
            districtWiseList.add(districtWise);

            previousCoronaVirusETLMetricsDTO = coronaVirusETLMetricsDTO;
        }
    }

    @Override
    public void load() {
        log.info("Step 3: executing Load");
        for (DistrictWise districtWise : districtWiseList) {
            try {
                districtWiseDao.insert(districtWise);
                //log.info("Inserting country wise data for country = {}, state = {}, value = {}", country, state, districtWise);
            }catch (Exception e) {
                log.error("Exception inserting country wise data for country = {}, state = {}, value = {}", country, state, districtWise);
            }
        }
    }

}
