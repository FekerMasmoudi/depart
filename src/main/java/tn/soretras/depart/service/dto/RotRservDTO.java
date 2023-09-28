package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.RotRserv} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RotRservDTO implements Serializable {

    @NotNull
    private Integer deccent;

    private Integer decagenc;

    @NotNull
    private String dedated;

    @NotNull
    private Integer matric;

    private String heur_deb;

    private String heur_fin;

    private String statut;

    private String liee_deb;

    private String liee_fin;

    private String program;

    private Integer cd1;

    private Integer cd2;

    private Integer cd3;

    private String id;

    private String annul;

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

    public Integer getMatric() {
        return matric;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public String getHeur_deb() {
        return heur_deb;
    }

    public void setHeur_deb(String heur_deb) {
        this.heur_deb = heur_deb;
    }

    public String getHeur_fin() {
        return heur_fin;
    }

    public void setHeur_fin(String heur_fin) {
        this.heur_fin = heur_fin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getLiee_deb() {
        return liee_deb;
    }

    public void setLieedeb(String liee_deb) {
        this.liee_deb = liee_deb;
    }

    public String getLiee_fin() {
        return liee_fin;
    }

    public void setLiee_fin(String liee_fin) {
        this.liee_fin = liee_fin;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Integer getCd1() {
        return cd1;
    }

    public void setCd1(Integer cd1) {
        this.cd1 = cd1;
    }

    public Integer getCd2() {
        return cd2;
    }

    public void setCd2(Integer cd2) {
        this.cd2 = cd2;
    }

    public Integer getCd3() {
        return cd3;
    }

    public void setCd3(Integer cd3) {
        this.cd3 = cd3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnnul() {
        return annul;
    }

    public void setAnnul(String annul) {
        this.annul = annul;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RotRservDTO)) {
            return false;
        }

        RotRservDTO rotRservDTO = (RotRservDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, rotRservDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RotRservDTO{" +
            "deccent=" + getDeccent() +
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
            ", id='" + getId() + "'" +
            ", annul='" + getAnnul() + "'" +
            "}";
    }
}
