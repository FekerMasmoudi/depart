package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Displaybus} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DisplaybusDTO implements Serializable {

    private String id;

    private String lang;

    private String vehicule;

    private Integer num_appel;

    private String detail_ligne;

    private String ligne;

    private String direction;

    private String denumli;

    private String deltyli;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public Integer getNum_appel() {
        return num_appel;
    }

    public void setNum_appel(Integer num_appel) {
        this.num_appel = num_appel;
    }

    public String getDetail_ligne() {
        return detail_ligne;
    }

    public void setDetail_ligne(String detail_ligne) {
        this.detail_ligne = detail_ligne;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDenumli() {
        return denumli;
    }

    public void setDenumli(String denumli) {
        this.denumli = denumli;
    }

    public String getDeltyli() {
        return deltyli;
    }

    public void setDeltyli(String deltyli) {
        this.deltyli = deltyli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DisplaybusDTO)) {
            return false;
        }

        DisplaybusDTO displaybusDTO = (DisplaybusDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, displaybusDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DisplaybusDTO{" +
            "id='" + getId() + "'" +
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
