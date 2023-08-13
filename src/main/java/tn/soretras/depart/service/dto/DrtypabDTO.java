package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Drtypab} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DrtypabDTO implements Serializable {

    private String id;

    private String cdtypab;

    private String lbtypab;

    private String dabsjt;

    private String dabsjp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCdtypab() {
        return cdtypab;
    }

    public void setCdtypab(String cdtypab) {
        this.cdtypab = cdtypab;
    }

    public String getLbtypab() {
        return lbtypab;
    }

    public void setLbtypab(String lbtypab) {
        this.lbtypab = lbtypab;
    }

    public String getDabsjt() {
        return dabsjt;
    }

    public void setDabsjt(String dabsjt) {
        this.dabsjt = dabsjt;
    }

    public String getDabsjp() {
        return dabsjp;
    }

    public void setDabsjp(String dabsjp) {
        this.dabsjp = dabsjp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DrtypabDTO)) {
            return false;
        }

        DrtypabDTO drtypabDTO = (DrtypabDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, drtypabDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DrtypabDTO{" +
            "id='" + getId() + "'" +
            ", cdtypab='" + getCdtypab() + "'" +
            ", lbtypab='" + getLbtypab() + "'" +
            ", dabsjt='" + getDabsjt() + "'" +
            ", dabsjp='" + getDabsjp() + "'" +
            "}";
    }
}
