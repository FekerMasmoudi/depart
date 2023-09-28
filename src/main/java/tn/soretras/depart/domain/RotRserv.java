package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A RotRserv.
 */
@Document(collection = "rot_rserv")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RotRserv implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Field("deccent")
    private Integer deccent;

    @Field("decagenc")
    private Integer decagenc;

    @NotNull
    @Field("dedated")
    private String dedated;

    @NotNull
    @Field("matric")
    private Integer matric;

    @Field("heurdeb")
    private String heur_deb;

    @Field("heurfin")
    private String heur_fin;

    @Field("statut")
    private String statut;

    @Field("lieedeb")
    private String liee_deb;

    @Field("lieefin")
    private String liee_fin;

    @Field("program")
    private String program;

    @Field("cd_1")
    private Integer cd1;

    @Field("cd_2")
    private Integer cd2;

    @Field("cd_3")
    private Integer cd3;

    @Id
    private String id;

    @Field("annul")
    private String annul;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getDeccent() {
        return this.deccent;
    }

    public RotRserv deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public RotRserv decagenc(Integer decagenc) {
        this.setDecagenc(decagenc);
        return this;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    public String getDedated() {
        return this.dedated;
    }

    public RotRserv dedated(String dedated) {
        this.setDedated(dedated);
        return this;
    }

    public void setDedated(String dedated) {
        this.dedated = dedated;
    }

    public Integer getMatric() {
        return this.matric;
    }

    public RotRserv matric(Integer matric) {
        this.setMatric(matric);
        return this;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public String getHeur_deb() {
        return this.heur_deb;
    }

    public RotRserv heur_deb(String heur_deb) {
        this.setHeur_deb(heur_deb);
        return this;
    }

    public void setHeur_deb(String heur_deb) {
        this.heur_deb = heur_deb;
    }

    public String getHeur_fin() {
        return this.heur_fin;
    }

    public RotRserv heur_fin(String heur_fin) {
        this.setHeur_fin(heur_fin);
        return this;
    }

    public void setHeur_fin(String heur_fin) {
        this.heur_fin = heur_fin;
    }

    public String getStatut() {
        return this.statut;
    }

    public RotRserv statut(String statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getLiee_deb() {
        return this.liee_deb;
    }

    public RotRserv liee_deb(String liee_deb) {
        this.setLiee_deb(liee_deb);
        return this;
    }

    public void setLiee_deb(String liee_deb) {
        this.liee_deb = liee_deb;
    }

    public String getLiee_fin() {
        return this.liee_fin;
    }

    public RotRserv liee_fin(String liee_fin) {
        this.setLiee_fin(liee_fin);
        return this;
    }

    public void setLiee_fin(String liee_fin) {
        this.liee_fin = liee_fin;
    }

    public String getProgram() {
        return this.program;
    }

    public RotRserv program(String program) {
        this.setProgram(program);
        return this;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Integer getCd1() {
        return this.cd1;
    }

    public RotRserv cd1(Integer cd1) {
        this.setCd1(cd1);
        return this;
    }

    public void setCd1(Integer cd1) {
        this.cd1 = cd1;
    }

    public Integer getCd2() {
        return this.cd2;
    }

    public RotRserv cd2(Integer cd2) {
        this.setCd2(cd2);
        return this;
    }

    public void setCd2(Integer cd2) {
        this.cd2 = cd2;
    }

    public Integer getCd3() {
        return this.cd3;
    }

    public RotRserv cd3(Integer cd3) {
        this.setCd3(cd3);
        return this;
    }

    public void setCd3(Integer cd3) {
        this.cd3 = cd3;
    }

    public String getId() {
        return this.id;
    }

    public RotRserv id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnnul() {
        return this.annul;
    }

    public RotRserv annul(String annul) {
        this.setAnnul(annul);
        return this;
    }

    public void setAnnul(String annul) {
        this.annul = annul;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RotRserv)) {
            return false;
        }
        return id != null && id.equals(((RotRserv) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RotRserv{" +
            "id=" + getId() +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", dedated='" + getDedated() + "'" +
            ", matric=" + getMatric() +
            ", heurdeb='" + getHeur_deb() + "'" +
            ", heurfin='" + getHeur_fin() + "'" +
            ", statut='" + getStatut() + "'" +
            ", lieedeb='" + getLiee_deb() + "'" +
            ", lieefin='" + getLiee_fin() + "'" +
            ", program='" + getProgram() + "'" +
            ", cd1=" + getCd1() +
            ", cd2=" + getCd2() +
            ", cd3=" + getCd3() +
            ", annul='" + getAnnul() + "'" +
            "}";
    }
}
