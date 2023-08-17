package tn.soretras.depart.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Station.
 */
@Document(collection = "station")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("decstat")
    private String decstat;

    @Field("dectyst")
    private String dectyst;

    @Field("decrout")
    private String decrout;

    @Field("delstat")
    private String delstat;

    @Field("delstatfr")
    private String delstatfr;

    @Field("lattitude")
    private String lattitude;

    @Field("longitude")
    private String longitude;

    @Field("valide")
    private String valide;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Station(
        String id,
        @NotNull String decstat,
        String dectyst,
        String decrout,
        String delstat,
        String delstatfr,
        String lattitude,
        String longitude,
        String valide
    ) {
        this.id = id;
        this.decstat = decstat;
        this.dectyst = dectyst;
        this.decrout = decrout;
        this.delstat = delstat;
        this.delstatfr = delstatfr;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.valide = valide;
    }

    public Station id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDecstat() {
        return this.decstat;
    }

    public Station decstat(String decstat) {
        this.setDecstat(decstat);
        return this;
    }

    public void setDecstat(String decstat) {
        this.decstat = decstat;
    }

    public String getDectyst() {
        return this.dectyst;
    }

    public Station dectyst(String dectyst) {
        this.setDectyst(dectyst);
        return this;
    }

    public void setDectyst(String dectyst) {
        this.dectyst = dectyst;
    }

    public String getDecrout() {
        return this.decrout;
    }

    public Station decrout(String decrout) {
        this.setDecrout(decrout);
        return this;
    }

    public void setDecrout(String decrout) {
        this.decrout = decrout;
    }

    public String getDelstat() {
        return this.delstat;
    }

    public Station delstat(String delstat) {
        this.setDelstat(delstat);
        return this;
    }

    public void setDelstat(String delstat) {
        this.delstat = delstat;
    }

    public String getDelstatfr() {
        return this.delstatfr;
    }

    public Station delstatfr(String delstatfr) {
        this.setDelstatfr(delstatfr);
        return this;
    }

    public void setDelstatfr(String delstatfr) {
        this.delstatfr = delstatfr;
    }

    public String getLattitude() {
        return this.lattitude;
    }

    public Station lattitude(String lattitude) {
        this.setLattitude(lattitude);
        return this;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public Station longitude(String longitude) {
        this.setLongitude(longitude);
        return this;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getValide() {
        return this.valide;
    }

    public Station valide(String valide) {
        this.setValide(valide);
        return this;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Station)) {
            return false;
        }
        return id != null && id.equals(((Station) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Station{" +
            "id=" + getId() +
            ", decstat='" + getDecstat() + "'" +
            ", dectyst='" + getDectyst() + "'" +
            ", decrout='" + getDecrout() + "'" +
            ", delstat='" + getDelstat() + "'" +
            ", delstatfr='" + getDelstatfr() + "'" +
            ", lattitude='" + getLattitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", valide='" + getValide() + "'" +
            "}";
    }
}
