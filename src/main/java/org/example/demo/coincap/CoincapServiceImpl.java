package org.example.demo.coincap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo.CoincapEntry;
import org.example.demo.repository.CoincapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CoincapServiceImpl implements CoincapService {

    public static String BITCOIN_HISTORY_URL = "https://api.coincap.io/v2/assets/bitcoin/history?interval=d1"; //process the data daily with the d1 keyword. if needed, m1 can be written to capture minute data.

    @Autowired
    private CoincapRepository coincapRepository;

    @Override
    public List<CoincapEntry> fetchSomeRecords() {
        try {

            final RestTemplate restTemplate = new RestTemplate();
            final String url = BITCOIN_HISTORY_URL;
            final ResponseEntity<String> response = restTemplate.getForEntity(url , String.class);

            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode root = mapper.readTree(response.getBody());
            final List<CoincapEntry> coincapEntries = new ArrayList<>();
            CoincapEntry coincapEntry;

            //saving 15 record for each fetch since there are too much data
            int i = 15;
            while(i>0){
                JsonNode jsonNode = root.get("data");

                coincapEntry = new CoincapEntry();
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                coincapEntry.setDate(LocalDateTime.parse(jsonNode.get(i).get("date").asText(), inputFormatter));
                coincapEntry.setPrice(new BigDecimal(jsonNode.get(i).get("priceUsd").asDouble()));

                coincapEntries.add(coincapEntry);
                i--;
            }

            return coincapEntries;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<CoincapEntry> get10Latest() {

        return coincapRepository.get10LatestRecords();
    }


    @Override
    public void deleteById(int id) {
        coincapRepository.deleteById(id);
    }
}
