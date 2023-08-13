package tn.soretras.depart.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A AffecAgent.
 */
@Document(collection = "affec_agent")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AffecAgent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("deccent")
    private Integer deccent;

    @Field("decagenc")
    private Integer decagenc;

    @Field("decserv")
    private Integer decserv;

    @Field("decoper")
    private String decoper;

    @Field("decsean")
    private String decsean;

    @Field("cdsocie")
    private String cdsocie;

    @Field("decexer")
    private Integer decexer;

    @Field("cdmois")
    private Integer cdmois;

    @Field("matric")
    private Integer matric;

    @Field("matric_2")
    private Integer matric2;

    @Field("cdmac")
    private String cdmac;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public AffecAgent id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public AffecAgent deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public AffecAgent decagenc(Integer decagenc) {
        this.setDecagenc(decagenc);
        return this;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    public Integer getDecserv() {
        return this.decserv;
    }

    public AffecAgent decserv(Integer decserv) {
        this.setDecserv(decserv);
        return this;
    }

    public void setDecserv(Integer decserv) {
        this.decserv = decserv;
    }

    public String getDecoper() {
        return this.decoper;
    }

    public AffecAgent decoper(String decoper) {
        this.setDecoper(decoper);
        return this;
    }

    public void setDecoper(String decoper) {
        this.decoper = decoper;
    }

    public String getDecsean() {
        return this.decsean;
    }

    public AffecAgent decsean(String decsean) {
        this.setDecsean(decsean);
        return this;
    }

    public void setDecsean(String decsean) {
        this.decsean = decsean;
    }

    public String getCdsocie() {
        return this.cdsocie;
    }

    public AffecAgent cdsocie(String cdsocie) {
        this.setCdsocie(cdsocie);
        return this;
    }

    public void setCdsocie(String cdsocie) {
        this.cdsocie = cdsocie;
    }

    public Integer getDecexer() {
        return this.decexer;
    }

    public AffecAgent decexer(Integer decexer) {
        this.setDecexer(decexer);
        return this;
    }

    public void setDecexer(Integer decexer) {
        this.decexer = decexer;
    }

    public Integer getCdmois() {
        return this.cdmois;
    }

    public AffecAgent cdmois(Integer cdmois) {
        this.setCdmois(cdmois);
        return this;
    }

    public void setCdmois(Integer cdmois) {
        this.cdmois = cdmois;
    }

    public Integer getMatric() {
        return this.matric;
    }

    public AffecAgent matric(Integer matric) {
        this.setMatric(matric);
        return this;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public Integer getMatric2() {
        return this.matric2;
    }

    public AffecAgent matric2(Integer matric2) {
        this.setMatric2(matric2);
        return this;
    }

    public void setMatric2(Integer matric2) {
        this.matric2 = matric2;
    }

    public String getCdmac() {
        return this.cdmac;
    }

    public AffecAgent cdmac(String cdmac) {
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
        if (!(o instanceof AffecAgent)) {
            return false;
        }
        return id != null && id.equals(((AffecAgent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AffecAgent{" +
            "id=" + getId() +
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
