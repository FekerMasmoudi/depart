package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Motifchserv} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MotifchservDTO implements Serializable {

    private String id;

    /* @NotNull
    private Integer decmotif;*/

    private String delmotif;

    private String x;

    private String vs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*  public Integer getDecmotif() {
        return decmotif;
    }

    public void setDecmotif(Integer decmotif) {
        this.decmotif = decmotif;
    }*/

    public String getDelmotif() {
        return delmotif;
    }

    public void setDelmotif(String delmotif) {
        this.delmotif = delmotif;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getVs() {
        return vs;
    }

    public void setVs(String vs) {
        this.vs = vs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MotifchservDTO)) {
            return false;
        }

        MotifchservDTO motifchservDTO = (MotifchservDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, motifchservDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MotifchservDTO{" +
            "id='" + getId() + "'" +
           
            ", delmotif='" + getDelmotif() + "'" +
            ", x='" + getX() + "'" +
            ", vs='" + getVs() + "'" +
            "}";
    }
}
