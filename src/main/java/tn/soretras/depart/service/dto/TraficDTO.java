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
    private LocalDate dedated;

    private Integer ancien;

    private String vldtrafic;

    private String clotrafic;

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

    public LocalDate getDedated() {
        return dedated;
    }

    public void setDedated(LocalDate dedated) {
        this.dedated = dedated;
    }

    public Integer getAncien() {
        return ancien;
    }

    public void setAncien(Integer ancien) {
        this.ancien = ancien;
    }

    public String getVldtrafic() {
        return vldtrafic;
    }

    public void setVldtrafic(String vldtrafic) {
        this.vldtrafic = vldtrafic;
    }

    public String getClotrafic() {
        return clotrafic;
    }

    public void setClotrafic(String clotrafic) {
        this.clotrafic = clotrafic;
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
            ", vldtrafic='" + getVldtrafic() + "'" +
            ", clotrafic='" + getClotrafic() + "'" +
            "}";
    }
}
