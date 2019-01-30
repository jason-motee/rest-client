import com.github.tomakehurst.wiremock.junit.WireMockRule;
import hygiene.Application;
import hygiene.FHRSEstablishment;
import hygiene.HygieneFetchService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class, properties = "food-health-ratings = localhost:8089")
@RunWith(SpringRunner.class)
public class HygieneFetchServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Autowired
    private HygieneFetchService hygieneFetchService;

    @Test
    public void test() {
        stubXmlFile();

        FHRSEstablishment data = hygieneFetchService.fetchData();
        assertThat(data.getEstablishmentCollection()).isNotNull();
    }

    @Test
    public void getFirstRestaurantInCollection() {
        stubXmlFile();

        FHRSEstablishment data = hygieneFetchService.fetchData();
        assertThat(data.getEstablishmentCollection().getEstablishmentDetail().get(0).getBusinessName()).isEqualTo("1847 Manchester");
    }


    private void stubXmlFile() {
        stubFor(get(urlEqualTo("/OpenDataFiles/FHRS415en-GB.xml"))
                .withHeader("Accept", equalTo("application/json, application/json, application/xml, application/*+json, application/*+json, text/xml, application/*+xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBodyFile("FHRS415en-GB.xml")));
    }


}