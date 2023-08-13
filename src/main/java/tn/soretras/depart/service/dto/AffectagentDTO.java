package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Affectagent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AffectagentDTO implements Serializable {

    private String id;

    @NotNull
    private Integer deccent;

    @NotNull
    private Integer decagenc;

    @NotNull
    private Integer decserv;

    @NotNull
    private String decoper;

    @NotNull
    private String decsean;

    @NotNull
    private Integer cdmois;

    @NotNull
    private String cdsocie;

    @NotNull
    private Integer decexer;

    @NotNull
    private Integer matric;

    @NotNull
    private Integer matric2;

    @NotNull
    private String cdmac;

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

    public Integer getDecserv() {
        return decserv;
    }

    public void setDecserv(Integer decserv) {
        this.decserv = decserv;
    }

    public String getDecoper() {
        return decoper;
    }

    public void setDecoper(String decoper) {
        this.decoper = decoper;
    }

    public String getDecsean() {
        return decsean;
    }

    public void setDecsean(String decsean) {
        this.decsean = decsean;
    }

    public Integer getCdmois() {
        return cdmois;
    }

    public void setCdmois(Integer cdmois) {
        this.cdmois = cdmois;
    }

    public String getCdsocie() {
        return cdsocie;
    }

    public void setCdsocie(String cdsocie) {
        this.cdsocie = cdsocie;
    }

    public Integer getDecexer() {
        return decexer;
    }

    public void setDecexer(Integer decexer) {
        this.decexer = decexer;
    }

    public Integer getMatric() {
        return matric;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public Integer getMatric2() {
        return matric2;
    }

    public void setMatric2(Integer matric2) {
        this.matric2 = matric2;
    }

    public String getCdmac() {
        return cdmac;
    }

    public void setCdmac(String cdmac) {
        this.cdmac = cdmac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AffectagentDTO)) {
            return false;
        }

        AffectagentDTO affectagentDTO = (AffectagentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, affectagentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AffectagentDTO{" +
            "id='" + getId() + "'" +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", decserv=" + getDecserv() +
            ", decoper='" + getDecoper() + "'" +
            ", decsean='" + getDecsean() + "'" +
            ", cdmois=" + getCdmois() +
            ", cdsocie='" + getCdsocie() + "'" +
            ", decexer=" + getDecexer() +
            ", matric=" + getMatric() +
            ", matric2=" + getMatric2() +
            ", cdmac='" + getCdmac() + "'" +
            "}";
    }
}
