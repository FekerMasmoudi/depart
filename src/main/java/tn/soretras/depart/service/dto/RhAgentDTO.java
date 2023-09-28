package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.RhAgent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RhAgentDTO implements Serializable {

    private String id;

    private Integer matric;

    private String decjour;

    private String dat_eff_rh;

    private String decoper;

    private Integer deccent;

    private Integer decagenc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMatric() {
        return matric;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public String getDecjour() {
        return decjour;
    }

    public void setDecjour(String decjour) {
        this.decjour = decjour;
    }

    public String getDat_eff_rh() {
        return dat_eff_rh;
    }

    public void setDat_eff_rh(String dat_eff_rh) {
        this.dat_eff_rh = dat_eff_rh;
    }

    public String getDecoper() {
        return decoper;
    }

    public void setDecoper(String decoper) {
        this.decoper = decoper;
    }

    public Integer getDeccent() {
        return deccent;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return decagenc;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RhAgentDTO)) {
            return false;
        }

        RhAgentDTO rhAgentDTO = (RhAgentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, rhAgentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RhAgentDTO{" +
            "id='" + getId() + "'" +
            ", matric=" + getMatric() +
            ", decjour='" + getDecjour() + "'" +
            ", dateffrh='" + getDat_eff_rh() + "'" +
            ", decoper='" + getDecoper() + "'" +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            "}";
    }
}
