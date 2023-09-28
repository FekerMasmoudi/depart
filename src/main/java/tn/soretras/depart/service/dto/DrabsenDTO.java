package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Drabsen} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DrabsenDTO implements Serializable {

    private String id;

    private String cdtypab;

    private Integer matric;

    private String dat_abs;

    private Integer num_abs;

    private Integer nbr_abs;

    private Integer valid_abs;

    private String observa_abs;

    private Integer cd1;

    private Integer cd2;

    private Integer cd3;

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

    public Integer getMatric() {
        return matric;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public String getDat_abs() {
        return dat_abs;
    }

    public void setDat_abs(String dat_abs) {
        this.dat_abs = dat_abs;
    }

    public Integer getNum_abs() {
        return num_abs;
    }

    public void setNum_abs(Integer num_abs) {
        this.num_abs = num_abs;
    }

    public Integer getNbr_abs() {
        return nbr_abs;
    }

    public void setNbr_abs(Integer nbr_abs) {
        this.nbr_abs = nbr_abs;
    }

    public Integer getValid_abs() {
        return valid_abs;
    }

    public void setValid_abs(Integer valid_abs) {
        this.valid_abs = valid_abs;
    }

    public String getObserva_abs() {
        return observa_abs;
    }

    public void setObserva_abs(String observa_abs) {
        this.observa_abs = observa_abs;
    }

    public Integer getCd1() {
        return cd1;
    }

    public void setCd1(Integer cd1) {
        this.cd1 = cd1;
    }

    public Integer getCd2() {
        return cd2;
    }

    public void setCd2(Integer cd2) {
        this.cd2 = cd2;
    }

    public Integer getCd3() {
        return cd3;
    }

    public void setCd3(Integer cd3) {
        this.cd3 = cd3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DrabsenDTO)) {
            return false;
        }

        DrabsenDTO drabsenDTO = (DrabsenDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, drabsenDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DrabsenDTO{" +
            "id='" + getId() + "'" +
            ", cdtypab='" + getCdtypab() + "'" +
            ", matric=" + getMatric() +
            ", databs='" + getDat_abs() + "'" +
            ", numabs=" + getNum_abs() +
            ", nbrabs=" + getNbr_abs() +
            ", validabs=" + getValid_abs() +
            ", observaabs='" + getObserva_abs() + "'" +
            ", cd1=" + getCd1() +
            ", cd2=" + getCd2() +
            ", cd3=" + getCd3() +
            "}";
    }
}
