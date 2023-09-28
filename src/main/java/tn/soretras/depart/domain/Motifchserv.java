package tn.soretras.depart.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Motifchserv.
 */
@Document(collection = "motifchserv")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Motifchserv implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /*@NotNull
    @Field("decmotif")
    private Integer decmotif;*/

    @Field("delmotif")
    private String delmotif;

    @Field("x")
    private String x;

    @Field("vs")
    private String vs;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Motifchserv() {}

    public Motifchserv id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*  public Integer getDecmotif() {
        return this.decmotif;
    }

    public Motifchserv decmotif(Integer decmotif) {
        this.setDecmotif(decmotif);
        return this;
    }

    public void setDecmotif(Integer decmotif) {
        this.decmotif = decmotif;
    }*/

    public String getDelmotif() {
        return this.delmotif;
    }

    public Motifchserv delmotif(String delmotif) {
        this.setDelmotif(delmotif);
        return this;
    }

    public void setDelmotif(String delmotif) {
        this.delmotif = delmotif;
    }

    public String getX() {
        return this.x;
    }

    public Motifchserv x(String x) {
        this.setX(x);
        return this;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getVs() {
        return this.vs;
    }

    public Motifchserv vs(String vs) {
        this.setVs(vs);
        return this;
    }

    public void setVs(String vs) {
        this.vs = vs;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Motifchserv)) {
            return false;
        }
        return id != null && id.equals(((Motifchserv) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Motifchserv{" +
            "id=" + getId() +
            
            ", delmotif='" + getDelmotif() + "'" +
            ", x='" + getX() + "'" +
            ", vs='" + getVs() + "'" +
            "}";
    }
}
