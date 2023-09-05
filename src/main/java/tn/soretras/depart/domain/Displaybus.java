package tn.soretras.depart.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Displaybus.
 */
@Document(collection = "displaybus")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Displaybus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("lang")
    private String lang;

    @Field("vehicule")
    private String vehicule;

    @Field("num_appel")
    private Integer num_appel;

    @Field("detail_ligne")
    private String detail_ligne;

    @Field("ligne")
    private String ligne;

    @Field("direction")
    private String direction;

    @Field("denumli")
    private String denumli;

    @Field("deltyli")
    private String deltyli;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Displaybus(
        String id,
        String lang,
        String vehicule,
        Integer num_appel,
        String detail_ligne,
        String ligne,
        String direction,
        String denumli,
        String deltyli
    ) {
        super();
        this.id = id;
        this.lang = lang;
        this.vehicule = vehicule;
        this.num_appel = num_appel;
        this.detail_ligne = detail_ligne;
        this.ligne = ligne;
        this.direction = direction;
        this.denumli = denumli;
        this.deltyli = deltyli;
    }

    public Displaybus id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLang() {
        return this.lang;
    }

    public Displaybus lang(String lang) {
        this.setLang(lang);
        return this;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getVehicule() {
        return this.vehicule;
    }

    public Displaybus vehicule(String vehicule) {
        this.setVehicule(vehicule);
        return this;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public Integer getNum_appel() {
        return this.num_appel;
    }

    public Displaybus num_appel(Integer num_appel) {
        this.setNum_appel(num_appel);
        return this;
    }

    public void setNum_appel(Integer num_appel) {
        this.num_appel = num_appel;
    }

    public String getDetail_ligne() {
        return this.detail_ligne;
    }

    public Displaybus detail_ligne(String detail_ligne) {
        this.setDetail_ligne(detail_ligne);
        return this;
    }

    public void setDetail_ligne(String detail_ligne) {
        this.detail_ligne = detail_ligne;
    }

    public String getLigne() {
        return this.ligne;
    }

    public Displaybus ligne(String ligne) {
        this.setLigne(ligne);
        return this;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public String getDirection() {
        return this.direction;
    }

    public Displaybus direction(String direction) {
        this.setDirection(direction);
        return this;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDenumli() {
        return this.denumli;
    }

    public Displaybus denumli(String denumli) {
        this.setDenumli(denumli);
        return this;
    }

    public void setDenumli(String denumli) {
        this.denumli = denumli;
    }

    public String getDeltyli() {
        return this.deltyli;
    }

    public Displaybus deltyli(String deltyli) {
        this.setDeltyli(deltyli);
        return this;
    }

    public void setDeltyli(String deltyli) {
        this.deltyli = deltyli;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Displaybus)) {
            return false;
        }
        return id != null && id.equals(((Displaybus) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Displaybus{" +
            "id=" + getId() +
            ", lang='" + getLang() + "'" +
            ", vehicule='" + getVehicule() + "'" +
            ", num_appel=" + getNum_appel() +
            ", detail_ligne='" + getDetail_ligne() + "'" +
            ", ligne='" + getLigne() + "'" +
            ", direction='" + getDirection() + "'" +
            ", denumli='" + getDenumli() + "'" +
            ", deltyli='" + getDeltyli() + "'" +
            "}";
    }
}
