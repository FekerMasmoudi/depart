package tn.soretras.depart.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Drtypab.
 */
@Document(collection = "drtypab")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Drtypab implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("cdtypab")
    private String cdtypab;

    @Field("lbtypab")
    private String lbtypab;

    @Field("dabsjt")
    private String dabsjt;

    @Field("dabsjp")
    private String dabsjp;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Drtypab(String id, String cdtypab, String lbtypab, String dabsjt, String dabsjp) {
        this.id = id;
        this.cdtypab = cdtypab;
        this.lbtypab = lbtypab;
        this.dabsjt = dabsjt;
        this.dabsjp = dabsjp;
    }

    public Drtypab() {}

    public Drtypab id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCdtypab() {
        return this.cdtypab;
    }

    public Drtypab cdtypab(String cdtypab) {
        this.setCdtypab(cdtypab);
        return this;
    }

    public void setCdtypab(String cdtypab) {
        this.cdtypab = cdtypab;
    }

    public String getLbtypab() {
        return this.lbtypab;
    }

    public Drtypab lbtypab(String lbtypab) {
        this.setLbtypab(lbtypab);
        return this;
    }

    public void setLbtypab(String lbtypab) {
        this.lbtypab = lbtypab;
    }

    public String getDabsjt() {
        return this.dabsjt;
    }

    public Drtypab dabsjt(String dabsjt) {
        this.setDabsjt(dabsjt);
        return this;
    }

    public void setDabsjt(String dabsjt) {
        this.dabsjt = dabsjt;
    }

    public String getDabsjp() {
        return this.dabsjp;
    }

    public Drtypab dabsjp(String dabsjp) {
        this.setDabsjp(dabsjp);
        return this;
    }

    public void setDabsjp(String dabsjp) {
        this.dabsjp = dabsjp;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Drtypab)) {
            return false;
        }
        return id != null && id.equals(((Drtypab) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Drtypab{" +
            "id=" + getId() +
            ", cdtypab='" + getCdtypab() + "'" +
            ", lbtypab='" + getLbtypab() + "'" +
            ", dabsjt='" + getDabsjt() + "'" +
            ", dabsjp='" + getDabsjp() + "'" +
            "}";
    }
}
