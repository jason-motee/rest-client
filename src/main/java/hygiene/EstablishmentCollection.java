package hygiene;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "EstablishmentCollection")
@XmlAccessorType(XmlAccessType.FIELD)
public class EstablishmentCollection {

    @XmlElement(name = "EstablishmentDetail")
    private List<EstablishmentDetail> establishmentDetailList = null;

    public List<EstablishmentDetail> getEstablishmentDetailList() {
        return establishmentDetailList;
    }

    public List<EstablishmentDetail> getEstablishmentDetail() {
        return establishmentDetailList;
    }

    public void setRestaurantDetail(List<EstablishmentDetail> establishmentDetailList) {
        this.establishmentDetailList = establishmentDetailList;
    }

    @Override
    public String toString() {
        return "EstablishmentCollection{" +
                "restaurantDetail=" + establishmentDetailList +
                '}';
    }
}
