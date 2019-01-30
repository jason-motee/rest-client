package hygiene;

import java.util.List;

public class HygieneService {

    private HygieneFetchService hygieneFetchService;

    public HygieneService(HygieneFetchService hygieneFetchService) {
        this.hygieneFetchService = hygieneFetchService;
    }

    public EstablishmentDetail getRestaurantByName(String name) {
        EstablishmentDetail restaurantFound = null;

        List <EstablishmentDetail> listOfRestaurants = hygieneFetchService.fetchData()
                .getEstablishmentCollection()
                .getEstablishmentDetailList();

        for (EstablishmentDetail restaurantDetail:listOfRestaurants) {
            if (name.equals(restaurantDetail.getBusinessName())){
                restaurantFound = restaurantDetail;
            }
        }
        return restaurantFound;
    }
}
