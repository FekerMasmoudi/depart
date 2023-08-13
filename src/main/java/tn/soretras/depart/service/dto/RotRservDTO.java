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

    @NotNull
    private Integer decagenc;

    @NotNull
    private LocalDate dedated;

    @NotNull
    private Integer matric;

    @NotNull
    private ZonedDateTime heurdeb;

    @NotNull
    private ZonedDateTime heurfin;

    private String statut;

    private ZonedDateTime lieedeb;

    private ZonedDateTime lieefin;

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

    public LocalDate getDedated() {
        return dedated;
    }

    public void setDedated(LocalDate dedated) {
        this.dedated = dedated;
    }

    public Integer getMatric() {
        return matric;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public ZonedDateTime getHeurdeb() {
        return heurdeb;
    }

    public void setHeurdeb(ZonedDateTime heurdeb) {
        this.heurdeb = heurdeb;
    }

    public ZonedDateTime getHeurfin() {
        return heurfin;
    }

    public void setHeurfin(ZonedDateTime heurfin) {
        this.heurfin = heurfin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public ZonedDateTime getLieedeb() {
        return lieedeb;
    }

    public void setLieedeb(ZonedDateTime lieedeb) {
        this.lieedeb = lieedeb;
    }

    public ZonedDateTime getLieefin() {
        return lieefin;
    }

    public void setLieefin(ZonedDateTime lieefin) {
        this.lieefin = lieefin;
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
            ", heurdeb='" + getHeurdeb() + "'" +
            ", heurfin='" + getHeurfin() + "'" +
            ", statut='" + getStatut() + "'" +
            ", lieedeb='" + getLieedeb() + "'" +
            ", lieefin='" + getLieefin() + "'" +
            ", program='" + getProgram() + "'" +
            ", cd1=" + getCd1() +
            ", cd2=" + getCd2() +
            ", cd3=" + getCd3() +
            ", id='" + getId() + "'" +
            ", annul='" + getAnnul() + "'" +
            "}";
    }
}
