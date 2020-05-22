package com.corona.insights.service;

import com.corona.insights.dao.DistrictWiseDaoImpl;
import com.corona.insights.dao.StateCodeNameMappingDaoImpl;
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
    StateCodeNameMappingDaoImpl stateCodeNameMappingDao;

    public List<HardestHitDO> getHardestHitDistricts(BigDecimal latitude, BigDecimal longitude, int radius) {
        log.info("Fetching hardest hit areas for latitude = {}, longitude = {} and radius = {}", latitude, longitude, radius);
        List<HardestHitDO> hardestHitDOS = districtWiseDao.getHardestHitDistricts(latitude, longitude, radius);
        updateZipCodes(hardestHitDOS);
        log.info("Fetched = {} hardest hit Districts for latitude = {}, longitude = {} and radius = {}", hardestHitDOS.size(), latitude, longitude, radius);
        return hardestHitDOS;
    }

    public List<HardestHitDO> getHardestHitDistricts(String country, String state, String district, int radius) {
        log.info("Fetching district for country = {}, state = {}, district = {}", country, state, district);
        DistrictWise districtWise = districtWiseDao.getDistrict(country, state, district);
        if (districtWise == null) throw new IllegalArgumentException("Invalid Country, state or district provided");
        return getHardestHitDistricts(districtWise.getLatitude(), districtWise.getLongitude(), radius);
    }

    public List<HardestHitDO> getHardestHitDistricts(int zipCode, int radius) {
        log.info("Fetching hardest hit areas zip code = {}", zipCode);
        ZipCodeMapping zipCodeMapping = zipCodeMappingDao.fetchOneByZip(zipCode);
        if (zipCodeMapping == null) throw new IllegalArgumentException("Invalid Zip code provided");
        return getHardestHitDistricts(zipCodeMapping.getLatitude(), zipCodeMapping.getLongitude(), radius);
    }

    private void updateZipCodes(List<HardestHitDO> hardestHitDOS) {

        for (HardestHitDO hardestHitDO : hardestHitDOS) {
            String stateCode = stateCodeNameMappingDao.getCodeForStateName(hardestHitDO.getState());
            String district = hardestHitDO.getDistrict();
            List<Integer> zipCodes = zipCodeMappingDao.getZipCodesForStateAndDistrict(stateCode, district);
            hardestHitDO.setZipCodes(zipCodes);
        }

    }

}
