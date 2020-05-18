package com.corona.insights.service;

import com.corona.insights.dao.DistrictWiseDaoImpl;
import com.corona.insights.dao.ZipCodeMappingDaoImpl;
import com.corona.insights.jooq.corona_insights.tables.pojos.DistrictWise;
import com.corona.insights.jooq.corona_insights.tables.pojos.ZipCodeMapping;
import com.corona.insights.model.HardestHitDO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CoronaHardestHitService {

    DistrictWiseDaoImpl districtWiseDao;
    ZipCodeMappingDaoImpl zipCodeMappingDao;

    public List<HardestHitDO> getHardestHitDistricts(BigDecimal latitude, BigDecimal longitude, int radius) {
        log.info("Fetching hardest hit areas for latitude = {}, longitude = {} and radius = {}", latitude, longitude, radius);
        return districtWiseDao.getHardestHitDistricts(latitude, longitude, radius);
    }

    public List<HardestHitDO> getHardestHitDistricts(String country, String state, String district, int radius) {
        log.info("Fetching district for country = {}, state = {}, district = {}", country, state, district);
        DistrictWise districtWise = districtWiseDao.getDistrict(country, state, district);
        return getHardestHitDistricts(districtWise.getLatitude(), districtWise.getLongitude(), radius);
    }

    public List<HardestHitDO> getHardestHitDistricts(int zipCode, int radius) {
        log.info("Fetching hardest hit areas zip code = {}", zipCode);
        ZipCodeMapping zipCodeMapping = zipCodeMappingDao.fetchOneByZip(zipCode);
        return getHardestHitDistricts(zipCodeMapping.getLatitude(), zipCodeMapping.getLongitude(), radius);
    }

}
