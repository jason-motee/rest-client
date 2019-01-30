import hygiene.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
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
    public void shouldFindARestaurantByName() {
        HygieneService hygieneService = new HygieneService(hygieneFetchService);
        when(hygieneFetchService.fetchData()).thenReturn(fakeFHRSEstablishment());

        List<EstablishmentDetail> expected = Collections.singletonList(fakeEstablishmentDetail("restaurant", "M15 4FN", "5"));
        List<EstablishmentDetail> actual = hygieneService.getRestaurantByName("restaurant");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenTwoRestaurantsHaveTheSameName_shouldReturnBoth(){
        EstablishmentDetail establishmentDetail1 = fakeEstablishmentDetail("same", "M15 4FN", "3");
        EstablishmentDetail establishmentDetail2 = fakeEstablishmentDetail("not same", "M4 4GN", "1");
        EstablishmentDetail establishmentDetail3 = fakeEstablishmentDetail("same", "M15 4YN", "5");

        List<EstablishmentDetail> establishmentDetails = new ArrayList<>();
        establishmentDetails.add(establishmentDetail1);
        establishmentDetails.add(establishmentDetail2);
        establishmentDetails.add(establishmentDetail3);

        EstablishmentCollection establishmentCollection = new EstablishmentCollection();
        establishmentCollection.setRestaurantDetail(establishmentDetails);

        FHRSEstablishment fhrsEstablishment2 = new FHRSEstablishment();
        fhrsEstablishment2.setEstablishmentCollection(establishmentCollection);

        HygieneService hygieneService = new HygieneService(hygieneFetchService);
        when(hygieneFetchService.fetchData()).thenReturn(fhrsEstablishment2);


        List<EstablishmentDetail> actual = hygieneService.getRestaurantByName("same");

        List <EstablishmentDetail> expected = new ArrayList<>();
        expected.add(fakeEstablishmentDetail("same", "M15 4FN", "3"));
        expected.add(fakeEstablishmentDetail("same", "M15 4YN", "5"));


        assertThat(actual).isEqualTo(expected);
    }

    private EstablishmentDetail fakeEstablishmentDetail(String name, String postCode, String healthRating) {
        EstablishmentDetail expected = new EstablishmentDetail();
        expected.setBusinessName(name);
        expected.setPostCode(postCode);
        expected.setRatingValue(healthRating);
        return expected;
    }

    private FHRSEstablishment fakeFHRSEstablishment() {
        EstablishmentDetail establishmentDetail1 = fakeEstablishmentDetail("restaurant", "M15 4FN", "5");
        EstablishmentDetail establishmentDetail2 = fakeEstablishmentDetail("restaurant2", "M12 4FN", "4");

        List<EstablishmentDetail> restaurantList = new ArrayList<>();
        restaurantList.add(establishmentDetail2);
        restaurantList.add(establishmentDetail1);

        EstablishmentCollection establishmentCollection = new EstablishmentCollection();
        establishmentCollection.setRestaurantDetail(restaurantList);

        FHRSEstablishment fhrsEstablishment = new FHRSEstablishment();
        fhrsEstablishment.setEstablishmentCollection(establishmentCollection);
        return fhrsEstablishment;
    }
}
