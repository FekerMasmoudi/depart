package tn.soretras.depart.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Motifa.
 */
@Document(collection = "motifa")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Motifa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("libmotif")
    private String libmotif;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Motifa() {}

    public Motifa id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibmotif() {
        return this.libmotif;
    }

    public Motifa libmotif(String libmotif) {
        this.setLibmotif(libmotif);
        return this;
    }

    public void setLibmotif(String libmotif) {
        this.libmotif = libmotif;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Motifa)) {
            return false;
        }
        return id != null && id.equals(((Motifa) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Motifa{" +
            "id=" + getId() +
            ", libmotif='" + getLibmotif() + "'" +
            "}";
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
