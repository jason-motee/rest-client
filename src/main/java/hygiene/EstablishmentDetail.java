package hygiene;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "EstablishmentDetail")
@XmlAccessorType(XmlAccessType.FIELD)
public class EstablishmentDetail {

    @XmlElement(name = "BusinessName")
    private String businessName;

    @XmlElement(name = "PostCode")
    private String postCode;

    @XmlElement(name = "RatingValue")
    private String ratingValue;

    public String getBusinessName() {
        return this.businessName;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setBusinessName(String name) {
        this.businessName = name;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    @Override
    public String toString() {
        return "EstablishmentDetail{" +
                "businessName='" + businessName + '\'' +
                ", postCode='" + postCode + '\'' +
                ", ratingValue='" + ratingValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstablishmentDetail that = (EstablishmentDetail) o;
        return Objects.equals(businessName, that.businessName) &&
                Objects.equals(postCode, that.postCode) &&
                Objects.equals(ratingValue, that.ratingValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessName, postCode, ratingValue);
    }
}
