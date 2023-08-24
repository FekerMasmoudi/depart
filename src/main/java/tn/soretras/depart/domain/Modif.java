package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Modif.
 */
@Document(collection = "modif")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Modif implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("deccent")
    private Integer deccent;

    @Field("decagenc")
    private Integer decagenc;

    @Field("dedated")
    private LocalDate dedated;

    @Field("denumdp")
    private Integer denumdp;

    @Field("decserv")
    private Integer decserv;

    @Field("decoper")
    private String decoper;

    @Field("decsean")
    private String decsean;

    @Field("numrotat")
    private Integer numrotat;

    @Field("matric")
    private Integer matric;

    @Field("cd_1")
    private Integer cd1;

    @Field("decmotif")
    private Integer decmotif;

    @Field("heur")
    private LocalDate heur;

    @Field("chre")
    private String chre;

    @Field("typ")
    private String typ;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Modif id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public Modif deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public Modif decagenc(Integer decagenc) {
        this.setDecagenc(decagenc);
        return this;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    public LocalDate getDedated() {
        return this.dedated;
    }

    public Modif dedated(LocalDate dedated) {
        this.setDedated(dedated);
        return this;
    }

    public void setDedated(LocalDate dedated) {
        this.dedated = dedated;
    }

    public Integer getDenumdp() {
        return this.denumdp;
    }

    public Modif denumdp(Integer denumdp) {
        this.setDenumdp(denumdp);
        return this;
    }

    public void setDenumdp(Integer denumdp) {
        this.denumdp = denumdp;
    }

    public Integer getDecserv() {
        return this.decserv;
    }

    public Modif decserv(Integer decserv) {
        this.setDecserv(decserv);
        return this;
    }

    public void setDecserv(Integer decserv) {
        this.decserv = decserv;
    }

    public String getDecoper() {
        return this.decoper;
    }

    public Modif decoper(String decoper) {
        this.setDecoper(decoper);
        return this;
    }

    public void setDecoper(String decoper) {
        this.decoper = decoper;
    }

    public String getDecsean() {
        return this.decsean;
    }

    public Modif decsean(String decsean) {
        this.setDecsean(decsean);
        return this;
    }

    public void setDecsean(String decsean) {
        this.decsean = decsean;
    }

    public Integer getNumrotat() {
        return this.numrotat;
    }

    public Modif numrotat(Integer numrotat) {
        this.setNumrotat(numrotat);
        return this;
    }

    public void setNumrotat(Integer numrotat) {
        this.numrotat = numrotat;
    }

    public Integer getMatric() {
        return this.matric;
    }

    public Modif matric(Integer matric) {
        this.setMatric(matric);
        return this;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public Integer getCd1() {
        return this.cd1;
    }

    public Modif cd1(Integer cd1) {
        this.setCd1(cd1);
        return this;
    }

    public void setCd1(Integer cd1) {
        this.cd1 = cd1;
    }

    public Integer getDecmotif() {
        return this.decmotif;
    }

    public Modif decmotif(Integer decmotif) {
        this.setDecmotif(decmotif);
        return this;
    }

    public void setDecmotif(Integer decmotif) {
        this.decmotif = decmotif;
    }

    public LocalDate getHeur() {
        return this.heur;
    }

    public Modif heur(LocalDate heur) {
        this.setHeur(heur);
        return this;
    }

    public void setHeur(LocalDate heur) {
        this.heur = heur;
    }

    public String getChre() {
        return this.chre;
    }

    public Modif chre(String chre) {
        this.setChre(chre);
        return this;
    }

    public void setChre(String chre) {
        this.chre = chre;
    }

    public String getTyp() {
        return this.typ;
    }

    public Modif typ(String typ) {
        this.setTyp(typ);
        return this;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Modif)) {
            return false;
        }
        return id != null && id.equals(((Modif) o).id);
    }

    public Modif(
        String id,
        Integer deccent,
        Integer decagenc,
        LocalDate dedated,
        Integer denumdp,
        Integer decserv,
        String decoper,
        String decsean,
        Integer numrotat,
        Integer matric,
        Integer cd1,
        Integer decmotif,
        LocalDate heur,
        String chre,
        String typ
    ) {
        this.id = id;
        this.deccent = deccent;
        this.decagenc = decagenc;
        this.dedated = dedated;
        this.denumdp = denumdp;
        this.decserv = decserv;
        this.decoper = decoper;
        this.decsean = decsean;
        this.numrotat = numrotat;
        this.matric = matric;
        this.cd1 = cd1;
        this.decmotif = decmotif;
        this.heur = heur;
        this.chre = chre;
        this.typ = typ;
    }

    public Modif() {}

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Modif{" +
            "id=" + getId() +
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
