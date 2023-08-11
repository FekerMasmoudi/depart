package tn.soretras.depart.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Periode.
 */
@Document(collection = "periode")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Periode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("decoper")
    private String decoper;

    @Field("denoper")
    private String denoper;

    @Field("primaire")
    private String primaire;

    @Field("startdate")
    private String startdate;

    @Field("enddate")
    private String enddate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Periode id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDecoper() {
        return this.decoper;
    }

    public Periode decoper(String decoper) {
        this.setDecoper(decoper);
        return this;
    }

    public void setDecoper(String decoper) {
        this.decoper = decoper;
    }

    public String getDenoper() {
        return this.denoper;
    }

    public Periode denoper(String denoper) {
        this.setDenoper(denoper);
        return this;
    }

    public void setDenoper(String denoper) {
        this.denoper = denoper;
    }

    public String getPrimaire() {
        return this.primaire;
    }

    public Periode primaire(String primaire) {
        this.setPrimaire(primaire);
        return this;
    }

    public void setPrimaire(String primaire) {
        this.primaire = primaire;
    }

    public String getStartdate() {
        return this.startdate;
    }

    public Periode startdate(String startdate) {
        this.setStartdate(startdate);
        return this;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return this.enddate;
    }

    public Periode enddate(String enddate) {
        this.setEnddate(enddate);
        return this;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Periode)) {
            return false;
        }
        return id != null && id.equals(((Periode) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Periode{" +
            "id=" + getId() +
            ", decoper='" + getDecoper() + "'" +
            ", denoper='" + getDenoper() + "'" +
            ", primaire='" + getPrimaire() + "'" +
            ", startdate='" + getStartdate() + "'" +
            ", enddate='" + getEnddate() + "'" +
            "}";
    }
}
