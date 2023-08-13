package tn.soretras.depart.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A ServiceRot.
 */
@Document(collection = "service_rot")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ServiceRot implements Serializable {

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
    @Field("codgrp")
    private Integer codgrp;

    @Field("delserv")
    private String delserv;

    @Field("ordserv")
    private Integer ordserv;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ServiceRot id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public ServiceRot deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public ServiceRot decagenc(Integer decagenc) {
        this.setDecagenc(decagenc);
        return this;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    public Integer getDecserv() {
        return this.decserv;
    }

    public ServiceRot decserv(Integer decserv) {
        this.setDecserv(decserv);
        return this;
    }

    public void setDecserv(Integer decserv) {
        this.decserv = decserv;
    }

    public Integer getCodgrp() {
        return this.codgrp;
    }

    public ServiceRot codgrp(Integer codgrp) {
        this.setCodgrp(codgrp);
        return this;
    }

    public void setCodgrp(Integer codgrp) {
        this.codgrp = codgrp;
    }

    public String getDelserv() {
        return this.delserv;
    }

    public ServiceRot delserv(String delserv) {
        this.setDelserv(delserv);
        return this;
    }

    public void setDelserv(String delserv) {
        this.delserv = delserv;
    }

    public Integer getOrdserv() {
        return this.ordserv;
    }

    public ServiceRot ordserv(Integer ordserv) {
        this.setOrdserv(ordserv);
        return this;
    }

    public void setOrdserv(Integer ordserv) {
        this.ordserv = ordserv;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceRot)) {
            return false;
        }
        return id != null && id.equals(((ServiceRot) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceRot{" +
            "id=" + getId() +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", decserv=" + getDecserv() +
            ", codgrp=" + getCodgrp() +
            ", delserv='" + getDelserv() + "'" +
            ", ordserv=" + getOrdserv() +
            "}";
    }
}
