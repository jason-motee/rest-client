package hygiene;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FHRSEstablishment")
@XmlAccessorType(XmlAccessType.FIELD)
public class FHRSEstablishment {

    @XmlElement(name = "EstablishmentCollection")
    private EstablishmentCollection establishmentCollection;

    public EstablishmentCollection getEstablishmentCollection() {
        return establishmentCollection;
    }

    public void setEstablishmentCollection(EstablishmentCollection establishmentCollection) {
        this.establishmentCollection = establishmentCollection;
    }

    @Override
    public String toString() {
        return "FHRSEstablishment{" +
                "establishmentCollection=" + establishmentCollection +
                '}';
    }
}
