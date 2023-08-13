package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A CentVehic.
 */
@Document(collection = "cent_vehic")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CentVehic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("cdmac")
    private String cdmac;

    @Field("dateff")
    private LocalDate dateff;

    @Field("deccent")
    private Integer deccent;

    @Field("decagenc")
    private Integer decagenc;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public CentVehic id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCdmac() {
        return this.cdmac;
    }

    public CentVehic cdmac(String cdmac) {
        this.setCdmac(cdmac);
        return this;
    }

    public void setCdmac(String cdmac) {
        this.cdmac = cdmac;
    }

    public LocalDate getDateff() {
        return this.dateff;
    }

    public CentVehic dateff(LocalDate dateff) {
        this.setDateff(dateff);
        return this;
    }

    public void setDateff(LocalDate dateff) {
        this.dateff = dateff;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public CentVehic deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public CentVehic decagenc(Integer decagenc) {
        this.setDecagenc(decagenc);
        return this;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CentVehic)) {
            return false;
        }
        return id != null && id.equals(((CentVehic) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CentVehic{" +
            "id=" + getId() +
            ", cdmac='" + getCdmac() + "'" +
            ", dateff='" + getDateff() + "'" +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            "}";
    }
}
