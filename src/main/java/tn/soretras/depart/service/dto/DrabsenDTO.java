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

    private LocalDate databs;

    private Integer numabs;

    private Integer nbrabs;

    private Integer validabs;

    private String observaabs;

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

    public LocalDate getDatabs() {
        return databs;
    }

    public void setDatabs(LocalDate databs) {
        this.databs = databs;
    }

    public Integer getNumabs() {
        return numabs;
    }

    public void setNumabs(Integer numabs) {
        this.numabs = numabs;
    }

    public Integer getNbrabs() {
        return nbrabs;
    }

    public void setNbrabs(Integer nbrabs) {
        this.nbrabs = nbrabs;
    }

    public Integer getValidabs() {
        return validabs;
    }

    public void setValidabs(Integer validabs) {
        this.validabs = validabs;
    }

    public String getObservaabs() {
        return observaabs;
    }

    public void setObservaabs(String observaabs) {
        this.observaabs = observaabs;
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
            ", databs='" + getDatabs() + "'" +
            ", numabs=" + getNumabs() +
            ", nbrabs=" + getNbrabs() +
            ", validabs=" + getValidabs() +
            ", observaabs='" + getObservaabs() + "'" +
            ", cd1=" + getCd1() +
            ", cd2=" + getCd2() +
            ", cd3=" + getCd3() +
            "}";
    }
}
