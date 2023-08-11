package tn.soretras.depart.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Affectagent.
 */
@Document(collection = "affectagent")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Affectagent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("deccent")
    private Integer deccent;

    @NotNull
    @Field("decagenc")
    private Integer decagenc;

    @NotNull
    @Field("decserv")
    private Integer decserv;

    @NotNull
    @Field("decoper")
    private String decoper;

    @NotNull
    @Field("decsean")
    private String decsean;

    @NotNull
    @Field("cdmois")
    private Integer cdmois;

    @NotNull
    @Field("cdsocie")
    private String cdsocie;

    @NotNull
    @Field("decexer")
    private Integer decexer;

    @NotNull
    @Field("matric")
    private Integer matric;

    @NotNull
    @Field("matric_2")
    private Integer matric2;

    @NotNull
    @Field("cdmac")
    private String cdmac;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Affectagent id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public Affectagent deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public Affectagent decagenc(Integer decagenc) {
        this.setDecagenc(decagenc);
        return this;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    public Integer getDecserv() {
        return this.decserv;
    }

    public Affectagent decserv(Integer decserv) {
        this.setDecserv(decserv);
        return this;
    }

    public void setDecserv(Integer decserv) {
        this.decserv = decserv;
    }

    public String getDecoper() {
        return this.decoper;
    }

    public Affectagent decoper(String decoper) {
        this.setDecoper(decoper);
        return this;
    }

    public void setDecoper(String decoper) {
        this.decoper = decoper;
    }

    public String getDecsean() {
        return this.decsean;
    }

    public Affectagent decsean(String decsean) {
        this.setDecsean(decsean);
        return this;
    }

    public void setDecsean(String decsean) {
        this.decsean = decsean;
    }

    public Integer getCdmois() {
        return this.cdmois;
    }

    public Affectagent cdmois(Integer cdmois) {
        this.setCdmois(cdmois);
        return this;
    }

    public void setCdmois(Integer cdmois) {
        this.cdmois = cdmois;
    }

    public String getCdsocie() {
        return this.cdsocie;
    }

    public Affectagent cdsocie(String cdsocie) {
        this.setCdsocie(cdsocie);
        return this;
    }

    public void setCdsocie(String cdsocie) {
        this.cdsocie = cdsocie;
    }

    public Integer getDecexer() {
        return this.decexer;
    }

    public Affectagent decexer(Integer decexer) {
        this.setDecexer(decexer);
        return this;
    }

    public void setDecexer(Integer decexer) {
        this.decexer = decexer;
    }

    public Integer getMatric() {
        return this.matric;
    }

    public Affectagent matric(Integer matric) {
        this.setMatric(matric);
        return this;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public Integer getMatric2() {
        return this.matric2;
    }

    public Affectagent matric2(Integer matric2) {
        this.setMatric2(matric2);
        return this;
    }

    public void setMatric2(Integer matric2) {
        this.matric2 = matric2;
    }

    public String getCdmac() {
        return this.cdmac;
    }

    public Affectagent cdmac(String cdmac) {
        this.setCdmac(cdmac);
        return this;
    }

    public void setCdmac(String cdmac) {
        this.cdmac = cdmac;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Affectagent)) {
            return false;
        }
        return id != null && id.equals(((Affectagent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Affectagent{" +
            "id=" + getId() +
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
