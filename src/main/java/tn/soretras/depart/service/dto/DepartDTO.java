package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;
import tn.soretras.depart.domain.Deprotat;

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
    private String dedated;

    @NotNull
    private Integer denumdp;

    private Integer matric;

    private Integer matric1;

    private Integer cdmac;

    private String deheups;

    private String deheufs;

    private Integer denbrro;

    private String deheuaa;

    private String deheudr;

    private String deheupd;

    private String deampli;

    private String obs_ind;

    private String vld_roul;

    private String deetat;

    private String deannul;

    private String decclot;

    private String execute;

    private String motif_a;

    private String observ;

    private Float recettes;

    private Integer nbre_voy;

    private Integer decmotifch;

    private Integer decmotifre;

    private Integer cd1;

    private Integer cd2;

    private Integer cd3;

    private Integer decmotifcha;

    private Integer decmotifrea;

    private Integer id_apex;

    private Set<DeprotatDTO> deprotats = new HashSet<>();

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

    public String getDeheups() {
        return deheups;
    }

    public void setDeheups(String deheups) {
        this.deheups = deheups;
    }

    public String getDeheufs() {
        return deheufs;
    }

    public void setDeheufs(String deheufs) {
        this.deheufs = deheufs;
    }

    public Integer getDenbrro() {
        return denbrro;
    }

    public void setDenbrro(Integer denbrro) {
        this.denbrro = denbrro;
    }

    public String getDeheuaa() {
        return deheuaa;
    }

    public void setDeheuaa(String deheuaa) {
        this.deheuaa = deheuaa;
    }

    public String getDeheudr() {
        return deheudr;
    }

    public void setDeheudr(String deheudr) {
        this.deheudr = deheudr;
    }

    public String getDeheupd() {
        return deheupd;
    }

    public void setDeheupd(String deheupd) {
        this.deheupd = deheupd;
    }

    public String getDeampli() {
        return deampli;
    }

    public void setDeampli(String deampli) {
        this.deampli = deampli;
    }

    public String getObs_ind() {
        return obs_ind;
    }

    public void setObs_ind(String obs_ind) {
        this.obs_ind = obs_ind;
    }

    public String getVld_roul() {
        return vld_roul;
    }

    public void setVld_roul(String vld_roul) {
        this.vld_roul = vld_roul;
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

    public String getMotif_a() {
        return motif_a;
    }

    public void setMotif_a(String motif_a) {
        this.motif_a = motif_a;
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

    public Integer getNbre_voy() {
        return nbre_voy;
    }

    public void setNbre_voy(Integer nbre_voy) {
        this.nbre_voy = nbre_voy;
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

    public Set<DeprotatDTO> getDeprotats() {
        return deprotats;
    }

    public void setDeprotats(Set<DeprotatDTO> deprotats) {
        this.deprotats = deprotats;
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
            ", obsind='" + getObs_ind() + "'" +
            ", vldroul='" + getVld_roul() + "'" +
            ", deetat='" + getDeetat() + "'" +
            ", deannul='" + getDeannul() + "'" +
            ", decclot='" + getDecclot() + "'" +
            ", execute='" + getExecute() + "'" +
            ", motifa='" + getMotif_a() + "'" +
            ", observ='" + getObserv() + "'" +
            ", recettes=" + getRecettes() +
            ", nbrevoy=" + getNbre_voy() +
            ", decmotifch=" + getDecmotifch() +
            ", decmotifre=" + getDecmotifre() +
            ", cd1=" + getCd1() +
            ", cd2=" + getCd2() +
            ", cd3=" + getCd3() +
            ", decmotifcha=" + getDecmotifcha() +
            ", decmotifrea=" + getDecmotifrea() +
            ", id_apex=" + getId_apex() +
            ", deprotats=" + getDeprotats() +
            "}";
    }

    public Integer getId_apex() {
        return id_apex;
    }

    public void setId_apex(Integer id_apex) {
        this.id_apex = id_apex;
    }
}
