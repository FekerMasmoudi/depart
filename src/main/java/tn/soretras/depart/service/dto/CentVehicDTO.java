package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.CentVehic} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CentVehicDTO implements Serializable {

    private String id;

    private String cdmac;

    private String dat_eff;

    private Integer deccent;

    private Integer decagenc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCdmac() {
        return cdmac;
    }

    public void setCdmac(String cdmac) {
        this.cdmac = cdmac;
    }

    public String getDat_eff() {
        return dat_eff;
    }

    public void setDateff(String dat_eff) {
        this.dat_eff = dat_eff;
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
        if (!(o instanceof CentVehicDTO)) {
            return false;
        }

        CentVehicDTO centVehicDTO = (CentVehicDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, centVehicDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CentVehicDTO{" +
            "id='" + getId() + "'" +
            ", cdmac='" + getCdmac() + "'" +
            ", dateff='" + getDat_eff() + "'" +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            "}";
    }
}
