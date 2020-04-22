package com.corona.insights.scheduler;


import com.corona.insights.dao.CountryWiseDaoImpl;
import com.corona.insights.jooq.corona_insights.tables.daos.CountryWiseDao;
import com.corona.insights.jooq.corona_insights.tables.pojos.CountryWise;
import com.corona.insights.model.CoronaVirusETLMetricsDTO;
import com.corona.insights.model.CoronaVirusReportDataModel;
import com.corona.insights.service.CoronaFileProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WebCrawlScheduler {

    private CountryWiseDaoImpl countryWiseDao;

    @Autowired
    public void setCountryWiseDao(CountryWiseDaoImpl countryWiseDao) {
        this.countryWiseDao = countryWiseDao;
    }

    @Scheduled(cron = "0 */30 * ? * *")
    public void crawlForData() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").get();
            Element element = doc.body().getElementById("main_table_countries_today");
            List<Node> nodes = element.childNodes();
            Node node = nodes.get(3);
            List<Node> childNodes = node.childNodes();
            List<Node> countryNodes = new ArrayList<>();
            for(int i=17; i < childNodes.size(); i+=2) {
                countryNodes.add(childNodes.get(i));
            }
            List<CountryWise> countryWises = new ArrayList<>();
            Date reportedDate =  Date.valueOf(LocalDate.now());
            Timestamp reportingTimestamp =  Timestamp.valueOf((LocalDateTime.now()));
            for (Node countryNode : countryNodes) {
                List<Node> statNodes = countryNode.childNodes();
                CountryWise countryWise = new CountryWise();
                String country = ((TextNode) statNodes.get(1).childNode(0).childNode(0)).text();
                countryWise.setCountry(country.equals("USA") ? "US" : country);
                try {
                    String confirmed = ((TextNode) statNodes.get(3).childNode(0)).text();
                    countryWise.setConfirmed(Long.valueOf(confirmed.replace(",","").trim()));
                }catch (Exception e) {
                    log.error("Exception while fetching the confirmed status for country ={}", country);
                }
                try {
                    String deaths = ((TextNode) statNodes.get(7).childNode(0)).text();
                    countryWise.setDeaths(Integer.valueOf(deaths.replace(",","").trim()));
                }catch (Exception e) {
                    log.error("Exception while fetching the deaths status for country ={}", country);
                }
                try {
                    String recovered = ((TextNode) statNodes.get(11).childNode(0)).text();
                    countryWise.setRecovered(Long.valueOf(recovered.replace(",","").trim()));
                }catch (Exception e) {
                    log.error("Exception while fetching the recovered status for country ={}", country);
                }
                countryWise.setReportingDate(reportedDate);
                countryWises.add(countryWise);
            }
        load(countryWises);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(List<CountryWise> countryWiseList) {
        for (CountryWise countryWise : countryWiseList) {
            try {
                countryWiseDao.deleteForReportingDate(countryWise.getReportingDate(), countryWise.getCountry());
                countryWiseDao.insert(countryWise);
                log.info("Inserting country wise data for country = {}, value = {}", countryWise.getCountry(), countryWise);
            }catch (Exception e) {
                log.error("Exception inserting country wise data for country = {}, value = {}", countryWise.getCountry(), countryWise);
            }
        }
    }

}