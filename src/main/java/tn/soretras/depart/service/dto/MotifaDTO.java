package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Motifa} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MotifaDTO implements Serializable {

    private String id;

    private String libmotif;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibmotif() {
        return libmotif;
    }

    public void setLibmotif(String libmotif) {
        this.libmotif = libmotif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MotifaDTO)) {
            return false;
        }

        MotifaDTO motifaDTO = (MotifaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, motifaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MotifaDTO{" +
            "id='" + getId() + "'" +
            ", libmotif='" + getLibmotif() + "'" +
            "}";
    }
}
