import hygiene.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HygieneServiceTest {

    @Mock
    private HygieneFetchService hygieneFetchService;
    private HygieneService hygieneService;

    @Before
    public void setUp() {
        initMocks(this);
        hygieneService = new HygieneService(hygieneFetchService);
    }

    @Test
    public void shouldFindARestaurantByName() {
        when(hygieneFetchService.fetchData()).thenReturn(
                makeFhrsEstablishment(
                        fakeEstablishmentDetail("restaurant", "M15 4FN", "5"),
                        fakeEstablishmentDetail("restaurant2", "M12 4FN", "4")));

        List <EstablishmentDetail> actual = hygieneService.getRestaurantByName("restaurant");

        List <EstablishmentDetail> expected = Collections.singletonList(fakeEstablishmentDetail("restaurant", "M15 4FN", "5"));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenTwoRestaurantsHaveTheSameName_shouldReturnBoth() {
        when(hygieneFetchService.fetchData()).thenReturn(
                makeFhrsEstablishment(
                        fakeEstablishmentDetail("same", "M15 4FN", "3"),
                        fakeEstablishmentDetail("not same", "M4 4GN", "1"),
                        fakeEstablishmentDetail("same", "M15 4YN", "5")));

        List <EstablishmentDetail> actual = hygieneService.getRestaurantByName("same");

        List <EstablishmentDetail> expected = new ArrayList<>();
        expected.add(fakeEstablishmentDetail("same", "M15 4FN", "3"));
        expected.add(fakeEstablishmentDetail("same", "M15 4YN", "5"));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldFindARestaurantByHealthRating() {
        when(hygieneFetchService.fetchData()).thenReturn(
                makeFhrsEstablishment(
                        fakeEstablishmentDetail("restaurant1", "M15 4FN", "5"),
                        fakeEstablishmentDetail("restaurant2", "M4 4GN", "5"),
                        fakeEstablishmentDetail("restaurant3", "M15 4YJ", "3"),
                        fakeEstablishmentDetail("restaurant4", "M15 4OJ", "1"),
                        fakeEstablishmentDetail("restaurant4", "M14 4OJ", "1"),
                        fakeEstablishmentDetail("restaurant6", "M25 4OJ", "1")));

        List <EstablishmentDetail> actual = hygieneService.getRestaurantByHealthRating("5");

        List <EstablishmentDetail> expected = new ArrayList<>();
        expected.add(fakeEstablishmentDetail("restaurant1", "M15 4FN", "5"));
        expected.add(fakeEstablishmentDetail("restaurant2", "M4 4GN", "5"));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldFindARestaurantByPostCode() {
        when(hygieneFetchService.fetchData()).thenReturn(
                makeFhrsEstablishment(
                        fakeEstablishmentDetail("restaurant1", "M15 4FN", "5"),
                        fakeEstablishmentDetail("restaurant2", "M4 4GN", "5"),
                        fakeEstablishmentDetail("restaurant3", "M15 4YJ", "3"),
                        fakeEstablishmentDetail("restaurant4", "M15 4OJ", "1")));

        List <EstablishmentDetail> actual = hygieneService.getRestaurantByPostCode("M15 5FN");

        List <EstablishmentDetail> expected = new ArrayList<>();
        expected.add(fakeEstablishmentDetail("restaurant1", "M15 4FN", "5"));
        expected.add(fakeEstablishmentDetail("restaurant3", "M15 4YJ", "3"));
        expected.add(fakeEstablishmentDetail("restaurant4", "M15 4OJ", "1"));

        assertThat(actual).isEqualTo(expected);
    }

    private EstablishmentDetail fakeEstablishmentDetail(String name, String postCode, String healthRating) {
        EstablishmentDetail expected = new EstablishmentDetail();
        expected.setBusinessName(name);
        expected.setPostCode(postCode);
        expected.setRatingValue(healthRating);
        return expected;
    }

    private FHRSEstablishment makeFhrsEstablishment(EstablishmentDetail... establishmentDetails) {
        List<EstablishmentDetail> restaurantList = Arrays.asList(establishmentDetails);

        EstablishmentCollection establishmentCollection = new EstablishmentCollection();
        establishmentCollection.setRestaurantDetail(restaurantList);

        FHRSEstablishment fhrsEstablishment = new FHRSEstablishment();
        fhrsEstablishment.setEstablishmentCollection(establishmentCollection);
        return fhrsEstablishment;
    }
}
