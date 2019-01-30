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
}
