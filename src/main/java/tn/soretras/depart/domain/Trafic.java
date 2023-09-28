package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Trafic.
 */
@Document(collection = "trafic")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Trafic implements Serializable {

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
    @Field("dedated")
    private String dedated;

    @Field("ancien")
    private Integer ancien;

    @Field("vldtrafic")
    private String vld_trafic;

    @Field("clotrafic")
    private String clo_trafic;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Trafic() {}

    public Trafic id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public Trafic deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public Trafic decagenc(Integer decagenc) {
        this.setDecagenc(decagenc);
        return this;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    public String getDedated() {
        return this.dedated;
    }

    public Trafic dedated(String dedated) {
        this.setDedated(dedated);
        return this;
    }

    public void setDedated(String dedated) {
        this.dedated = dedated;
    }

    public Integer getAncien() {
        return this.ancien;
    }

    public Trafic ancien(Integer ancien) {
        this.setAncien(ancien);
        return this;
    }

    public void setAncien(Integer ancien) {
        this.ancien = ancien;
    }

    public String getVld_trafic() {
        return this.vld_trafic;
    }

    public Trafic vld_trafic(String vld_trafic) {
        this.setVld_trafic(vld_trafic);
        return this;
    }

    public void setVld_trafic(String vld_trafic) {
        this.vld_trafic = vld_trafic;
    }

    public String getClo_trafic() {
        return this.clo_trafic;
    }

    public Trafic clo_trafic(String clo_trafic) {
        this.setClo_trafic(clo_trafic);
        return this;
    }

    public void setClo_trafic(String clo_trafic) {
        this.clo_trafic = clo_trafic;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Trafic)) {
            return false;
        }
        return id != null && id.equals(((Trafic) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Trafic{" +
            "id=" + getId() +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", dedated='" + getDedated() + "'" +
            ", ancien=" + getAncien() +
            ", vldtrafic='" + getVld_trafic() + "'" +
            ", clotrafic='" + getClo_trafic() + "'" +
            "}";
    }
}
