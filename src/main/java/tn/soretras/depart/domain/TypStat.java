package tn.soretras.depart.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A TypStat.
 */
@Document(collection = "typ_stat")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TypStat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("dectyst")
    private String dectyst;

    @Field("deltyst")
    private String deltyst;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public TypStat id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDectyst() {
        return this.dectyst;
    }

    public TypStat dectyst(String dectyst) {
        this.setDectyst(dectyst);
        return this;
    }

    public void setDectyst(String dectyst) {
        this.dectyst = dectyst;
    }

    public String getDeltyst() {
        return this.deltyst;
    }

    public TypStat deltyst(String deltyst) {
        this.setDeltyst(deltyst);
        return this;
    }

    public void setDeltyst(String deltyst) {
        this.deltyst = deltyst;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypStat)) {
            return false;
        }
        return id != null && id.equals(((TypStat) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypStat{" +
            "id=" + getId() +
            ", dectyst='" + getDectyst() + "'" +
            ", deltyst='" + getDeltyst() + "'" +
            "}";
    }
}
