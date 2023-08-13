package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.AffecAgent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AffecAgentDTO implements Serializable {

    private String id;

    private Integer deccent;

    private Integer decagenc;

    private Integer decserv;

    private String decoper;

    private String decsean;

    private String cdsocie;

    private Integer decexer;

    private Integer cdmois;

    private Integer matric;

    private Integer matric2;

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

    public Integer getCdmois() {
        return cdmois;
    }

    public void setCdmois(Integer cdmois) {
        this.cdmois = cdmois;
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
        if (!(o instanceof AffecAgentDTO)) {
            return false;
        }

        AffecAgentDTO affecAgentDTO = (AffecAgentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, affecAgentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AffecAgentDTO{" +
            "id='" + getId() + "'" +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", decserv=" + getDecserv() +
            ", decoper='" + getDecoper() + "'" +
            ", decsean='" + getDecsean() + "'" +
            ", cdsocie='" + getCdsocie() + "'" +
            ", decexer=" + getDecexer() +
            ", cdmois=" + getCdmois() +
            ", matric=" + getMatric() +
            ", matric2=" + getMatric2() +
            ", cdmac='" + getCdmac() + "'" +
            "}";
    }
}
