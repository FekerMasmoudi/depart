package tn.soretras.depart.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Center.
 */
@Document(collection = "center")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Center implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("deccent")
    private Integer deccent;

    @Field("delcent")
    private String delcent;

    @Field("deadrce")
    private String deadrce;

    @Field("deobser")
    private String deobser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Center(String id, @NotNull Integer deccent, String delcent, String deadrce, String deobser) {
        this.id = id;
        this.deccent = deccent;
        this.delcent = delcent;
        this.deadrce = deadrce;
        this.deobser = deobser;
    }

    public Center() {}

    public Center id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public Center deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public String getDelcent() {
        return this.delcent;
    }

    public Center delcent(String delcent) {
        this.setDelcent(delcent);
        return this;
    }

    public void setDelcent(String delcent) {
        this.delcent = delcent;
    }

    public String getDeadrce() {
        return this.deadrce;
    }

    public Center deadrce(String deadrce) {
        this.setDeadrce(deadrce);
        return this;
    }

    public void setDeadrce(String deadrce) {
        this.deadrce = deadrce;
    }

    public String getDeobser() {
        return this.deobser;
    }

    public Center deobser(String deobser) {
        this.setDeobser(deobser);
        return this;
    }

    public void setDeobser(String deobser) {
        this.deobser = deobser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Center)) {
            return false;
        }
        return id != null && id.equals(((Center) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Center{" +
            "id=" + getId() +
            ", deccent=" + getDeccent() +
            ", delcent='" + getDelcent() + "'" +
            ", deadrce='" + getDeadrce() + "'" +
            ", deobser='" + getDeobser() + "'" +
            "}";
    }
}
