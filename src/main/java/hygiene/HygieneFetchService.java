package hygiene;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class HygieneFetchService {

    @Value("${food-health-ratings:ratings.food.gov.uk}")
    private String hostName;
    private RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(HygieneFetchService.class);

    public HygieneFetchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FHRSEstablishment fetchData() {
        HttpHeaders headers = new HttpHeaders();
        String url = "http://" + hostName + "/OpenDataFiles/FHRS415en-GB.xml";
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_XML));

        ResponseEntity<FHRSEstablishment> restaurantDetail = restTemplate.exchange(url, HttpMethod.GET, entity, FHRSEstablishment.class);
        log.info(restaurantDetail.getBody().toString());
        return restaurantDetail.getBody();
    }
}
