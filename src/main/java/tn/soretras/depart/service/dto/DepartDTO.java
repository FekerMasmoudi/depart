package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Depart} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DepartDTO implements Serializable {

    private String id;

    @NotNull
    private Integer deccent;

    @NotNull
    private Integer decagenc;

    @NotNull
    private Integer decserv;

    @NotNull
    private String decoper;

    @NotNull
    private String decsean;

    @NotNull
    private LocalDate dedated;

    @NotNull
    private Integer denumdp;

    private Integer matric;

    private Integer matric1;

    private Integer cdmac;

    private ZonedDateTime deheups;

    private ZonedDateTime deheufs;

    private Integer denbrro;

    private ZonedDateTime deheuaa;

    private ZonedDateTime deheudr;

    private ZonedDateTime deheupd;

    private ZonedDateTime deampli;

    private String obsind;

    private String vldroul;

    private String deetat;

    private String deannul;

    private String decclot;

    private String execute;

    private String motifa;

    private String observ;

    private Float recettes;

    private Integer nbrevoy;

    private Integer decmotifch;

    private Integer decmotifre;

    private Integer cd1;

    private Integer cd2;

    private Integer cd3;

    private Integer decmotifcha;

    private Integer decmotifrea;

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

    public LocalDate getDedated() {
        return dedated;
    }

    public void setDedated(LocalDate dedated) {
        this.dedated = dedated;
    }

    public Integer getDenumdp() {
        return denumdp;
    }

    public void setDenumdp(Integer denumdp) {
        this.denumdp = denumdp;
    }

    public Integer getMatric() {
        return matric;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public Integer getMatric1() {
        return matric1;
    }

    public void setMatric1(Integer matric1) {
        this.matric1 = matric1;
    }

    public Integer getCdmac() {
        return cdmac;
    }

    public void setCdmac(Integer cdmac) {
        this.cdmac = cdmac;
    }

    public ZonedDateTime getDeheups() {
        return deheups;
    }

    public void setDeheups(ZonedDateTime deheups) {
        this.deheups = deheups;
    }

    public ZonedDateTime getDeheufs() {
        return deheufs;
    }

    public void setDeheufs(ZonedDateTime deheufs) {
        this.deheufs = deheufs;
    }

    public Integer getDenbrro() {
        return denbrro;
    }

    public void setDenbrro(Integer denbrro) {
        this.denbrro = denbrro;
    }

    public ZonedDateTime getDeheuaa() {
        return deheuaa;
    }

    public void setDeheuaa(ZonedDateTime deheuaa) {
        this.deheuaa = deheuaa;
    }

    public ZonedDateTime getDeheudr() {
        return deheudr;
    }

    public void setDeheudr(ZonedDateTime deheudr) {
        this.deheudr = deheudr;
    }

    public ZonedDateTime getDeheupd() {
        return deheupd;
    }

    public void setDeheupd(ZonedDateTime deheupd) {
        this.deheupd = deheupd;
    }

    public ZonedDateTime getDeampli() {
        return deampli;
    }

    public void setDeampli(ZonedDateTime deampli) {
        this.deampli = deampli;
    }

    public String getObsind() {
        return obsind;
    }

    public void setObsind(String obsind) {
        this.obsind = obsind;
    }

    public String getVldroul() {
        return vldroul;
    }

    public void setVldroul(String vldroul) {
        this.vldroul = vldroul;
    }

    public String getDeetat() {
        return deetat;
    }

    public void setDeetat(String deetat) {
        this.deetat = deetat;
    }

    public String getDeannul() {
        return deannul;
    }

    public void setDeannul(String deannul) {
        this.deannul = deannul;
    }

    public String getDecclot() {
        return decclot;
    }

    public void setDecclot(String decclot) {
        this.decclot = decclot;
    }

    public String getExecute() {
        return execute;
    }

    public void setExecute(String execute) {
        this.execute = execute;
    }

    public String getMotifa() {
        return motifa;
    }

    public void setMotifa(String motifa) {
        this.motifa = motifa;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public Float getRecettes() {
        return recettes;
    }

    public void setRecettes(Float recettes) {
        this.recettes = recettes;
    }

    public Integer getNbrevoy() {
        return nbrevoy;
    }

    public void setNbrevoy(Integer nbrevoy) {
        this.nbrevoy = nbrevoy;
    }

    public Integer getDecmotifch() {
        return decmotifch;
    }

    public void setDecmotifch(Integer decmotifch) {
        this.decmotifch = decmotifch;
    }

    public Integer getDecmotifre() {
        return decmotifre;
    }

    public void setDecmotifre(Integer decmotifre) {
        this.decmotifre = decmotifre;
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

    public Integer getDecmotifcha() {
        return decmotifcha;
    }

    public void setDecmotifcha(Integer decmotifcha) {
        this.decmotifcha = decmotifcha;
    }

    public Integer getDecmotifrea() {
        return decmotifrea;
    }

    public void setDecmotifrea(Integer decmotifrea) {
        this.decmotifrea = decmotifrea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepartDTO)) {
            return false;
        }

        DepartDTO departDTO = (DepartDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, departDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepartDTO{" +
            "id='" + getId() + "'" +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", decserv=" + getDecserv() +
            ", decoper='" + getDecoper() + "'" +
            ", decsean='" + getDecsean() + "'" +
            ", dedated='" + getDedated() + "'" +
            ", denumdp=" + getDenumdp() +
            ", matric=" + getMatric() +
            ", matric1=" + getMatric1() +
            ", cdmac=" + getCdmac() +
            ", deheups='" + getDeheups() + "'" +
            ", deheufs='" + getDeheufs() + "'" +
            ", denbrro=" + getDenbrro() +
            ", deheuaa='" + getDeheuaa() + "'" +
            ", deheudr='" + getDeheudr() + "'" +
            ", deheupd='" + getDeheupd() + "'" +
            ", deampli='" + getDeampli() + "'" +
            ", obsind='" + getObsind() + "'" +
            ", vldroul='" + getVldroul() + "'" +
            ", deetat='" + getDeetat() + "'" +
            ", deannul='" + getDeannul() + "'" +
            ", decclot='" + getDecclot() + "'" +
            ", execute='" + getExecute() + "'" +
            ", motifa='" + getMotifa() + "'" +
            ", observ='" + getObserv() + "'" +
            ", recettes=" + getRecettes() +
            ", nbrevoy=" + getNbrevoy() +
            ", decmotifch=" + getDecmotifch() +
            ", decmotifre=" + getDecmotifre() +
            ", cd1=" + getCd1() +
            ", cd2=" + getCd2() +
            ", cd3=" + getCd3() +
            ", decmotifcha=" + getDecmotifcha() +
            ", decmotifrea=" + getDecmotifrea() +
            "}";
    }
}
