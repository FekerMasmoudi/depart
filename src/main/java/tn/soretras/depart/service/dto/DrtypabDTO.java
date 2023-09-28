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

    private String dabs_jt;

    private String dabs_jp;

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

    public String getDabs_jt() {
        return dabs_jt;
    }

    public void setDabs_jt(String dabs_jt) {
        this.dabs_jt = dabs_jt;
    }

    public String getDabs_jp() {
        return dabs_jp;
    }

    public void setDabs_jp(String dabs_jp) {
        this.dabs_jp = dabs_jp;
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
            ", dabsjt='" + getDabs_jt() + "'" +
            ", dabsjp='" + getDabs_jp() + "'" +
            "}";
    }
}
