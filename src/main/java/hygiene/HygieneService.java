package hygiene;

import java.util.ArrayList;
import java.util.List;

public class HygieneService {

    private HygieneFetchService hygieneFetchService;

    public HygieneService(HygieneFetchService hygieneFetchService) {
        this.hygieneFetchService = hygieneFetchService;
    }

    public List <EstablishmentDetail> getRestaurantByName(String name) {
        List <EstablishmentDetail> restaurantFound = new ArrayList<>();

        List <EstablishmentDetail> listOfRestaurants = hygieneFetchService.fetchData()
                .getEstablishmentCollection()
                .getEstablishmentDetailList();

        for (EstablishmentDetail restaurantDetail:listOfRestaurants) {
            if (name.equals(restaurantDetail.getBusinessName())){
                restaurantFound.add(restaurantDetail);
            }
        }
        return restaurantFound;
    }

    public List<EstablishmentDetail> getRestaurantByHealthRating(String healthRating) {
        List <EstablishmentDetail> restaurantFound = new ArrayList<>();

        List <EstablishmentDetail> listOfRestaurants = hygieneFetchService.fetchData()
                .getEstablishmentCollection()
                .getEstablishmentDetailList();

        for (EstablishmentDetail restaurantDetail:listOfRestaurants) {
            if (healthRating.equals(restaurantDetail.getRatingValue())){
                restaurantFound.add(restaurantDetail);
            }
        }
        return restaurantFound;
    }

    public List<EstablishmentDetail> getRestaurantByPostCode(String postCode) {
        List<EstablishmentDetail> restaurantFound = new ArrayList<>();

        List<EstablishmentDetail> listOfRestaurants = hygieneFetchService.fetchData()
                .getEstablishmentCollection()
                .getEstablishmentDetailList();

        for (EstablishmentDetail restaurantDetail : listOfRestaurants) {
            if (postCode.startsWith(restaurantDetail.getPostCode().substring(0,3))) {
                restaurantFound.add(restaurantDetail);
            }
        }
        return restaurantFound;
    }
}
