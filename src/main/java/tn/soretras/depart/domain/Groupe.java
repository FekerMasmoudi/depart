package tn.soretras.depart.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Groupe.
 */
@Document(collection = "groupe")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Groupe implements Serializable {

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
    @Field("codgrp")
    private Integer codgrp;

    @Field("libgrp")
    private String libgrp;

    @Field("dectyli")
    private String dectyli;

    @Field("libgrpfr")
    private String libgrpfr;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Groupe id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public Groupe deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public Groupe decagenc(Integer decagenc) {
        this.setDecagenc(decagenc);
        return this;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    public Integer getCodgrp() {
        return this.codgrp;
    }

    public Groupe codgrp(Integer codgrp) {
        this.setCodgrp(codgrp);
        return this;
    }

    public void setCodgrp(Integer codgrp) {
        this.codgrp = codgrp;
    }

    public String getLibgrp() {
        return this.libgrp;
    }

    public Groupe libgrp(String libgrp) {
        this.setLibgrp(libgrp);
        return this;
    }

    public void setLibgrp(String libgrp) {
        this.libgrp = libgrp;
    }

    public String getDectyli() {
        return this.dectyli;
    }

    public Groupe dectyli(String dectyli) {
        this.setDectyli(dectyli);
        return this;
    }

    public void setDectyli(String dectyli) {
        this.dectyli = dectyli;
    }

    public String getLibgrpfr() {
        return this.libgrpfr;
    }

    public Groupe libgrpfr(String libgrpfr) {
        this.setLibgrpfr(libgrpfr);
        return this;
    }

    public void setLibgrpfr(String libgrpfr) {
        this.libgrpfr = libgrpfr;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Groupe)) {
            return false;
        }
        return id != null && id.equals(((Groupe) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Groupe{" +
            "id=" + getId() +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", codgrp=" + getCodgrp() +
            ", libgrp='" + getLibgrp() + "'" +
            ", dectyli='" + getDectyli() + "'" +
            ", libgrpfr='" + getLibgrpfr() + "'" +
            "}";
    }
}
