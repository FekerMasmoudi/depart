package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A RhAgent.
 */
@Document(collection = "rh_agent")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RhAgent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("matric")
    private Integer matric;

    @Field("decjour")
    private String decjour;

    @Field("dateffrh")
    private LocalDate dateffrh;

    @Field("decoper")
    private String decoper;

    @Field("deccent")
    private Integer deccent;

    @Field("decagenc")
    private Integer decagenc;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public RhAgent(String id, Integer matric, String decjour, LocalDate dateffrh, String decoper, Integer deccent, Integer decagenc) {
        this.id = id;
        this.matric = matric;
        this.decjour = decjour;
        this.dateffrh = dateffrh;
        this.decoper = decoper;
        this.deccent = deccent;
        this.decagenc = decagenc;
    }

    public RhAgent id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMatric() {
        return this.matric;
    }

    public RhAgent matric(Integer matric) {
        this.setMatric(matric);
        return this;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public String getDecjour() {
        return this.decjour;
    }

    public RhAgent decjour(String decjour) {
        this.setDecjour(decjour);
        return this;
    }

    public void setDecjour(String decjour) {
        this.decjour = decjour;
    }

    public LocalDate getDateffrh() {
        return this.dateffrh;
    }

    public RhAgent dateffrh(LocalDate dateffrh) {
        this.setDateffrh(dateffrh);
        return this;
    }

    public void setDateffrh(LocalDate dateffrh) {
        this.dateffrh = dateffrh;
    }

    public String getDecoper() {
        return this.decoper;
    }

    public RhAgent decoper(String decoper) {
        this.setDecoper(decoper);
        return this;
    }

    public void setDecoper(String decoper) {
        this.decoper = decoper;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public RhAgent deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public RhAgent decagenc(Integer decagenc) {
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
        if (!(o instanceof RhAgent)) {
            return false;
        }
        return id != null && id.equals(((RhAgent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RhAgent{" +
            "id=" + getId() +
            ", matric=" + getMatric() +
            ", decjour='" + getDecjour() + "'" +
            ", dateffrh='" + getDateffrh() + "'" +
            ", decoper='" + getDecoper() + "'" +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            "}";
    }
}
