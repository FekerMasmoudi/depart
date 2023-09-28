package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Station} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StationDTO implements Serializable {

    private String id;

    @NotNull
    private String decstat;

    private String dectyst;

    private String decrout;

    private String delstat;

    private String delstat_fr;

    private String lattitude;

    private String longitude;

    private String valide;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDecstat() {
        return decstat;
    }

    public void setDecstat(String decstat) {
        this.decstat = decstat;
    }

    public String getDectyst() {
        return dectyst;
    }

    public void setDectyst(String dectyst) {
        this.dectyst = dectyst;
    }

    public String getDecrout() {
        return decrout;
    }

    public void setDecrout(String decrout) {
        this.decrout = decrout;
    }

    public String getDelstat() {
        return delstat;
    }

    public void setDelstat(String delstat) {
        this.delstat = delstat;
    }

    public String getDelstat_fr() {
        return delstat_fr;
    }

    public void setDelstat_fr(String delstat_fr) {
        this.delstat_fr = delstat_fr;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StationDTO)) {
            return false;
        }

        StationDTO stationDTO = (StationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, stationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StationDTO{" +
            "id='" + getId() + "'" +
            ", decstat='" + getDecstat() + "'" +
            ", dectyst='" + getDectyst() + "'" +
            ", decrout='" + getDecrout() + "'" +
            ", delstat='" + getDelstat() + "'" +
            ", delstatfr='" + getDelstat_fr() + "'" +
            ", lattitude='" + getLattitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", valide='" + getValide() + "'" +
            "}";
    }
}
