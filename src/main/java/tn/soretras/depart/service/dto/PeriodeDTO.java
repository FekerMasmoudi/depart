package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Periode} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PeriodeDTO implements Serializable {

    private String id;

    private String decoper;

    private String denoper;

    private String primaire;

    private String start_date;

    private String end_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDecoper() {
        return decoper;
    }

    public void setDecoper(String decoper) {
        this.decoper = decoper;
    }

    public String getDenoper() {
        return denoper;
    }

    public void setDenoper(String denoper) {
        this.denoper = denoper;
    }

    public String getPrimaire() {
        return primaire;
    }

    public void setPrimaire(String primaire) {
        this.primaire = primaire;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PeriodeDTO)) {
            return false;
        }

        PeriodeDTO periodeDTO = (PeriodeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, periodeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PeriodeDTO{" +
            "id='" + getId() + "'" +
            ", decoper='" + getDecoper() + "'" +
            ", denoper='" + getDenoper() + "'" +
            ", primaire='" + getPrimaire() + "'" +
            ", startdate='" + getStart_date() + "'" +
            ", enddate='" + getEnd_date() + "'" +
            "}";
    }
}
