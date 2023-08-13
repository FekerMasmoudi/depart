package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.TypStat} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TypStatDTO implements Serializable {

    private String id;

    @NotNull
    private String dectyst;

    private String deltyst;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDectyst() {
        return dectyst;
    }

    public void setDectyst(String dectyst) {
        this.dectyst = dectyst;
    }

    public String getDeltyst() {
        return deltyst;
    }

    public void setDeltyst(String deltyst) {
        this.deltyst = deltyst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypStatDTO)) {
            return false;
        }

        TypStatDTO typStatDTO = (TypStatDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, typStatDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypStatDTO{" +
            "id='" + getId() + "'" +
            ", dectyst='" + getDectyst() + "'" +
            ", deltyst='" + getDeltyst() + "'" +
            "}";
    }
}
