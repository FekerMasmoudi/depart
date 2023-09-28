package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Modif} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ModifDTO implements Serializable {

    private String id;

    private Integer deccent;

    private Integer decagenc;

    private String dedated;

    private Integer denumdp;

    private Integer decserv;

    private String decoper;

    private String decsean;

    private Integer numrotat;

    private Integer matric;

    private Integer cd1;

    private Integer decmotif;

    private String heur;

    private String chre;

    private String typ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeccent() {
        return deccent;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return decagenc;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    public String getDedated() {
        return dedated;
    }

    public void setDedated(String dedated) {
        this.dedated = dedated;
    }

    public Integer getDenumdp() {
        return denumdp;
    }

    public void setDenumdp(Integer denumdp) {
        this.denumdp = denumdp;
    }

    public Integer getDecserv() {
        return decserv;
    }

    public void setDecserv(Integer decserv) {
        this.decserv = decserv;
    }

    public String getDecoper() {
        return decoper;
    }

    public void setDecoper(String decoper) {
        this.decoper = decoper;
    }

    public String getDecsean() {
        return decsean;
    }

    public void setDecsean(String decsean) {
        this.decsean = decsean;
    }

    public Integer getNumrotat() {
        return numrotat;
    }

    public void setNumrotat(Integer numrotat) {
        this.numrotat = numrotat;
    }

    public Integer getMatric() {
        return matric;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public Integer getCd1() {
        return cd1;
    }

    public void setCd1(Integer cd1) {
        this.cd1 = cd1;
    }

    public Integer getDecmotif() {
        return decmotif;
    }

    public void setDecmotif(Integer decmotif) {
        this.decmotif = decmotif;
    }

    public String getHeur() {
        return heur;
    }

    public void setHeur(String heur) {
        this.heur = heur;
    }

    public String getChre() {
        return chre;
    }

    public void setChre(String chre) {
        this.chre = chre;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModifDTO)) {
            return false;
        }

        ModifDTO modifDTO = (ModifDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, modifDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ModifDTO{" +
            "id='" + getId() + "'" +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", dedated='" + getDedated() + "'" +
            ", denumdp=" + getDenumdp() +
            ", decserv=" + getDecserv() +
            ", decoper='" + getDecoper() + "'" +
            ", decsean='" + getDecsean() + "'" +
            ", numrotat=" + getNumrotat() +
            ", matric=" + getMatric() +
            ", cd1=" + getCd1() +
            ", decmotif=" + getDecmotif() +
            ", heur='" + getHeur() + "'" +
            ", chre='" + getChre() + "'" +
            ", typ='" + getTyp() + "'" +
            "}";
    }
}
