import hygiene.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HygieneServiceTest {

    @Mock
    HygieneFetchService hygieneFetchService;

    @Before
    public void setUp(){
        initMocks(this);

    }

    @Test
    public void findARestaurantByName() {
        EstablishmentDetail establishmentDetail1 = new EstablishmentDetail();
        establishmentDetail1.setBusinessName("restaurant");
        establishmentDetail1.setPostCode("M15 4FN");
        establishmentDetail1.setRatingValue("5");

        EstablishmentDetail establishmentDetail2 = new EstablishmentDetail();
        establishmentDetail2.setBusinessName("restaurant2");
        establishmentDetail2.setPostCode("M12 4FN");
        establishmentDetail2.setRatingValue("4");

        List<EstablishmentDetail> restaurantList = new ArrayList<>();
        restaurantList.add(establishmentDetail2);
        restaurantList.add(establishmentDetail1);

        EstablishmentCollection establishmentCollection = new EstablishmentCollection();
        establishmentCollection.setRestaurantDetail(restaurantList);

        FHRSEstablishment fhrsEstablishment = new FHRSEstablishment();
        fhrsEstablishment.setEstablishmentCollection(establishmentCollection);


        when(hygieneFetchService.fetchData()).thenReturn(fhrsEstablishment);

        HygieneService hygieneService = new HygieneService(hygieneFetchService);

        EstablishmentDetail actual = hygieneService.getRestaurantByName("restaurant");
        EstablishmentDetail expected = new EstablishmentDetail();
        expected.setBusinessName("restaurant");
        expected.setPostCode("M15 4FN");
        expected.setRatingValue("5");

        assertThat(actual).isEqualTo(expected);
    }
}
