package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Trafic} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TraficDTO implements Serializable {

    private String id;

    @NotNull
    private Integer deccent;

    @NotNull
    private Integer decagenc;

    @NotNull
    private String dedated;

    private Integer ancien;

    private String vld_trafic;

    private String clo_trafic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDedated() {
        return dedated;
    }

    public void setDedated(String dedated) {
        this.dedated = dedated;
    }

    public Integer getAncien() {
        return ancien;
    }

    public void setAncien(Integer ancien) {
        this.ancien = ancien;
    }

    public String getVld_trafic() {
        return vld_trafic;
    }

    public void setVld_trafic(String vld_trafic) {
        this.vld_trafic = vld_trafic;
    }

    public String getClo_trafic() {
        return clo_trafic;
    }

    public void setClo_trafic(String clo_trafic) {
        this.clo_trafic = clo_trafic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TraficDTO)) {
            return false;
        }

        TraficDTO traficDTO = (TraficDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, traficDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TraficDTO{" +
            "id='" + getId() + "'" +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", dedated='" + getDedated() + "'" +
            ", ancien=" + getAncien() +
            ", vldtrafic='" + getVld_trafic() + "'" +
            ", clotrafic='" + getClo_trafic() + "'" +
            "}";
    }
}
