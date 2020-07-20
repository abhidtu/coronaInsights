package com.corona.insights.etl;

import com.corona.insights.dao.CasesDaoImpl;
import com.corona.insights.dao.StateWiseImplDao;
import com.corona.insights.jooq.corona_insights.enums.StateWiseSource;
import com.corona.insights.jooq.corona_insights.tables.pojos.StateWise;
import com.corona.insights.model.CoronaVirusETLMetricsDTO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CoronaInsightsStateETLProcessor implements ETLProcessor {

    private String country;
    private String state;
    private Timestamp cutOffDate;
    private CasesDaoImpl casesDao;
    private StateWiseImplDao stateWiseDao;
    private List<CoronaVirusETLMetricsDTO> coronaVirusETLMetricsDTOList;
    private List<StateWise> stateWiseList;

    public CoronaInsightsStateETLProcessor(String country,String state, Timestamp cutOffDate, CasesDaoImpl casesDao, StateWiseImplDao stateWiseDao) {
        this.country = country;
        this.state = state;
        this.cutOffDate = cutOffDate;
        this.casesDao = casesDao;
        this.stateWiseDao = stateWiseDao;
        this.stateWiseList = new ArrayList<>();
    }

    @Override
    public void extract() {
        log.info("Step 1: executing Extract for country = {}, state = {}", country, state);
        coronaVirusETLMetricsDTOList = cutOffDate == null ? casesDao.aggregateDataForState(state) : casesDao.aggregateDataForStateWithCutOffDate(state, cutOffDate);
    }

    @Override
    public void transform() {
        log.info("Step 2: executing Transform for country = {}, state = {}", country, state);
        CoronaVirusETLMetricsDTO previousCoronaVirusETLMetricsDTO = new CoronaVirusETLMetricsDTO();
        for (CoronaVirusETLMetricsDTO coronaVirusETLMetricsDTO : coronaVirusETLMetricsDTOList) {
            StateWise stateWise = new StateWise();
            stateWise.setCountry(country);
            stateWise.setState(state);

            stateWise.setConfirmed(coronaVirusETLMetricsDTO.getConfirmed());
            stateWise.setDeaths(coronaVirusETLMetricsDTO.getDeaths());
            stateWise.setRecovered(coronaVirusETLMetricsDTO.getRecovered());
            stateWise.setActive(coronaVirusETLMetricsDTO.getConfirmed() - coronaVirusETLMetricsDTO.getDeaths() - coronaVirusETLMetricsDTO.getRecovered());

            coronaVirusETLMetricsDTO.computeDelta(previousCoronaVirusETLMetricsDTO);

            stateWise.setDeltaConfirmed(coronaVirusETLMetricsDTO.getDeltaConfirmed());
            stateWise.setDeltaDeaths(coronaVirusETLMetricsDTO.getDeltaDeaths());
            stateWise.setDeltaRecovered(coronaVirusETLMetricsDTO.getDeltaRecovered());
            stateWise.setDeltaActive(coronaVirusETLMetricsDTO.getDeltaActive());

            stateWise.setReportingDate(coronaVirusETLMetricsDTO.getReportedDate());
            stateWise.setSource(StateWiseSource.JHU);
            stateWiseList.add(stateWise);

            previousCoronaVirusETLMetricsDTO = coronaVirusETLMetricsDTO;
        }
    }

    @Override
    public void load() {
        log.info("Step 3: executing Load");
        for (StateWise stateWise : stateWiseList) {
            try {
                stateWiseDao.insert(stateWise);
                //log.info("Inserting country wise data for country = {}, state = {}, value = {}", country, state, stateWise);
            }catch (Exception e) {
                log.error("Exception inserting country wise data for country = {}, state = {}, value = {}", country, state, stateWiseList);
            }
        }
    }
}
