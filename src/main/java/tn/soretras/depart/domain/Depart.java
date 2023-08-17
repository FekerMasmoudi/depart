package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Depart.
 */
@Document(collection = "depart")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Depart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("deccent")
    private Integer deccent;

    @NotNull
    @Field("decagenc")
    private Integer decagenc;

    @NotNull
    @Field("decserv")
    private Integer decserv;

    @NotNull
    @Field("decoper")
    private String decoper;

    @NotNull
    @Field("decsean")
    private String decsean;

    @NotNull
    @Field("dedated")
    private LocalDate dedated;

    @NotNull
    @Field("denumdp")
    private Integer denumdp;

    @Field("matric")
    private Integer matric;

    @Field("matric_1")
    private Integer matric1;

    @Field("cdmac")
    private Integer cdmac;

    @Field("deheups")
    private LocalDate deheups;

    @Field("deheufs")
    private LocalDate deheufs;

    @Field("denbrro")
    private Integer denbrro;

    @Field("deheuaa")
    private LocalDate deheuaa;

    @Field("deheudr")
    private LocalDate deheudr;

    @Field("deheupd")
    private LocalDate deheupd;

    @Field("deampli")
    private LocalDate deampli;

    @Field("obsind")
    private String obsind;

    @Field("vldroul")
    private String vldroul;

    @Field("deetat")
    private String deetat;

    @Field("deannul")
    private String deannul;

    @Field("decclot")
    private String decclot;

    @Field("execute")
    private String execute;

    @Field("motifa")
    private String motifa;

    @Field("observ")
    private String observ;

    @Field("recettes")
    private Float recettes;

    @Field("nbrevoy")
    private Integer nbrevoy;

    @Field("decmotifch")
    private Integer decmotifch;

    @Field("decmotifre")
    private Integer decmotifre;

    @Field("cd_1")
    private Integer cd1;

    @Field("cd_2")
    private Integer cd2;

    @Field("cd_3")
    private Integer cd3;

    @Field("decmotifcha")
    private Integer decmotifcha;

    @Field("decmotifrea")
    private Integer decmotifrea;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Depart(
        String id,
        @NotNull Integer deccent,
        @NotNull Integer decagenc,
        @NotNull Integer decserv,
        @NotNull String decoper,
        @NotNull String decsean,
        @NotNull LocalDate dedated,
        @NotNull Integer denumdp,
        Integer matric,
        Integer matric1,
        Integer cdmac,
        LocalDate deheups,
        LocalDate deheufs,
        Integer denbrro,
        LocalDate deheuaa,
        LocalDate deheudr,
        LocalDate deheupd,
        LocalDate deampli,
        String obsind,
        String vldroul,
        String deetat,
        String deannul,
        String decclot,
        String execute,
        String motifa,
        String observ,
        Float recettes,
        Integer nbrevoy,
        Integer decmotifch,
        Integer decmotifre,
        Integer cd1,
        Integer cd2,
        Integer cd3,
        Integer decmotifcha,
        Integer decmotifrea
    ) {
        this.id = id;
        this.deccent = deccent;
        this.decagenc = decagenc;
        this.decserv = decserv;
        this.decoper = decoper;
        this.decsean = decsean;
        this.dedated = dedated;
        this.denumdp = denumdp;
        this.matric = matric;
        this.matric1 = matric1;
        this.cdmac = cdmac;
        this.deheups = deheups;
        this.deheufs = deheufs;
        this.denbrro = denbrro;
        this.deheuaa = deheuaa;
        this.deheudr = deheudr;
        this.deheupd = deheupd;
        this.deampli = deampli;
        this.obsind = obsind;
        this.vldroul = vldroul;
        this.deetat = deetat;
        this.deannul = deannul;
        this.decclot = decclot;
        this.execute = execute;
        this.motifa = motifa;
        this.observ = observ;
        this.recettes = recettes;
        this.nbrevoy = nbrevoy;
        this.decmotifch = decmotifch;
        this.decmotifre = decmotifre;
        this.cd1 = cd1;
        this.cd2 = cd2;
        this.cd3 = cd3;
        this.decmotifcha = decmotifcha;
        this.decmotifrea = decmotifrea;
    }

    public String getId() {
        return this.id;
    }

    public Depart id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public Depart deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public Depart decagenc(Integer decagenc) {
        this.setDecagenc(decagenc);
        return this;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    public Integer getDecserv() {
        return this.decserv;
    }

    public Depart decserv(Integer decserv) {
        this.setDecserv(decserv);
        return this;
    }

    public void setDecserv(Integer decserv) {
        this.decserv = decserv;
    }

    public String getDecoper() {
        return this.decoper;
    }

    public Depart decoper(String decoper) {
        this.setDecoper(decoper);
        return this;
    }

    public void setDecoper(String decoper) {
        this.decoper = decoper;
    }

    public String getDecsean() {
        return this.decsean;
    }

    public Depart decsean(String decsean) {
        this.setDecsean(decsean);
        return this;
    }

    public void setDecsean(String decsean) {
        this.decsean = decsean;
    }

    public LocalDate getDedated() {
        return this.dedated;
    }

    public Depart dedated(LocalDate dedated) {
        this.setDedated(dedated);
        return this;
    }

    public void setDedated(LocalDate dedated) {
        this.dedated = dedated;
    }

    public Integer getDenumdp() {
        return this.denumdp;
    }

    public Depart denumdp(Integer denumdp) {
        this.setDenumdp(denumdp);
        return this;
    }

    public void setDenumdp(Integer denumdp) {
        this.denumdp = denumdp;
    }

    public Integer getMatric() {
        return this.matric;
    }

    public Depart matric(Integer matric) {
        this.setMatric(matric);
        return this;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public Integer getMatric1() {
        return this.matric1;
    }

    public Depart matric1(Integer matric1) {
        this.setMatric1(matric1);
        return this;
    }

    public void setMatric1(Integer matric1) {
        this.matric1 = matric1;
    }

    public Integer getCdmac() {
        return this.cdmac;
    }

    public Depart cdmac(Integer cdmac) {
        this.setCdmac(cdmac);
        return this;
    }

    public void setCdmac(Integer cdmac) {
        this.cdmac = cdmac;
    }

    public LocalDate getDeheups() {
        return this.deheups;
    }

    public Depart deheups(LocalDate deheups) {
        this.setDeheups(deheups);
        return this;
    }

    public void setDeheups(LocalDate deheups) {
        this.deheups = deheups;
    }

    public LocalDate getDeheufs() {
        return this.deheufs;
    }

    public Depart deheufs(LocalDate deheufs) {
        this.setDeheufs(deheufs);
        return this;
    }

    public void setDeheufs(LocalDate deheufs) {
        this.deheufs = deheufs;
    }

    public Integer getDenbrro() {
        return this.denbrro;
    }

    public Depart denbrro(Integer denbrro) {
        this.setDenbrro(denbrro);
        return this;
    }

    public void setDenbrro(Integer denbrro) {
        this.denbrro = denbrro;
    }

    public LocalDate getDeheuaa() {
        return this.deheuaa;
    }

    public Depart deheuaa(LocalDate deheuaa) {
        this.setDeheuaa(deheuaa);
        return this;
    }

    public void setDeheuaa(LocalDate deheuaa) {
        this.deheuaa = deheuaa;
    }

    public LocalDate getDeheudr() {
        return this.deheudr;
    }

    public Depart deheudr(LocalDate deheudr) {
        this.setDeheudr(deheudr);
        return this;
    }

    public void setDeheudr(LocalDate deheudr) {
        this.deheudr = deheudr;
    }

    public LocalDate getDeheupd() {
        return this.deheupd;
    }

    public Depart deheupd(LocalDate deheupd) {
        this.setDeheupd(deheupd);
        return this;
    }

    public void setDeheupd(LocalDate deheupd) {
        this.deheupd = deheupd;
    }

    public LocalDate getDeampli() {
        return this.deampli;
    }

    public Depart deampli(LocalDate deampli) {
        this.setDeampli(deampli);
        return this;
    }

    public void setDeampli(LocalDate deampli) {
        this.deampli = deampli;
    }

    public String getObsind() {
        return this.obsind;
    }

    public Depart obsind(String obsind) {
        this.setObsind(obsind);
        return this;
    }

    public void setObsind(String obsind) {
        this.obsind = obsind;
    }

    public String getVldroul() {
        return this.vldroul;
    }

    public Depart vldroul(String vldroul) {
        this.setVldroul(vldroul);
        return this;
    }

    public void setVldroul(String vldroul) {
        this.vldroul = vldroul;
    }

    public String getDeetat() {
        return this.deetat;
    }

    public Depart deetat(String deetat) {
        this.setDeetat(deetat);
        return this;
    }

    public void setDeetat(String deetat) {
        this.deetat = deetat;
    }

    public String getDeannul() {
        return this.deannul;
    }

    public Depart deannul(String deannul) {
        this.setDeannul(deannul);
        return this;
    }

    public void setDeannul(String deannul) {
        this.deannul = deannul;
    }

    public String getDecclot() {
        return this.decclot;
    }

    public Depart decclot(String decclot) {
        this.setDecclot(decclot);
        return this;
    }

    public void setDecclot(String decclot) {
        this.decclot = decclot;
    }

    public String getExecute() {
        return this.execute;
    }

    public Depart execute(String execute) {
        this.setExecute(execute);
        return this;
    }

    public void setExecute(String execute) {
        this.execute = execute;
    }

    public String getMotifa() {
        return this.motifa;
    }

    public Depart motifa(String motifa) {
        this.setMotifa(motifa);
        return this;
    }

    public void setMotifa(String motifa) {
        this.motifa = motifa;
    }

    public String getObserv() {
        return this.observ;
    }

    public Depart observ(String observ) {
        this.setObserv(observ);
        return this;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public Float getRecettes() {
        return this.recettes;
    }

    public Depart recettes(Float recettes) {
        this.setRecettes(recettes);
        return this;
    }

    public void setRecettes(Float recettes) {
        this.recettes = recettes;
    }

    public Integer getNbrevoy() {
        return this.nbrevoy;
    }

    public Depart nbrevoy(Integer nbrevoy) {
        this.setNbrevoy(nbrevoy);
        return this;
    }

    public void setNbrevoy(Integer nbrevoy) {
        this.nbrevoy = nbrevoy;
    }

    public Integer getDecmotifch() {
        return this.decmotifch;
    }

    public Depart decmotifch(Integer decmotifch) {
        this.setDecmotifch(decmotifch);
        return this;
    }

    public void setDecmotifch(Integer decmotifch) {
        this.decmotifch = decmotifch;
    }

    public Integer getDecmotifre() {
        return this.decmotifre;
    }

    public Depart decmotifre(Integer decmotifre) {
        this.setDecmotifre(decmotifre);
        return this;
    }

    public void setDecmotifre(Integer decmotifre) {
        this.decmotifre = decmotifre;
    }

    public Integer getCd1() {
        return this.cd1;
    }

    public Depart cd1(Integer cd1) {
        this.setCd1(cd1);
        return this;
    }

    public void setCd1(Integer cd1) {
        this.cd1 = cd1;
    }

    public Integer getCd2() {
        return this.cd2;
    }

    public Depart cd2(Integer cd2) {
        this.setCd2(cd2);
        return this;
    }

    public void setCd2(Integer cd2) {
        this.cd2 = cd2;
    }

    public Integer getCd3() {
        return this.cd3;
    }

    public Depart cd3(Integer cd3) {
        this.setCd3(cd3);
        return this;
    }

    public void setCd3(Integer cd3) {
        this.cd3 = cd3;
    }

    public Integer getDecmotifcha() {
        return this.decmotifcha;
    }

    public Depart decmotifcha(Integer decmotifcha) {
        this.setDecmotifcha(decmotifcha);
        return this;
    }

    public void setDecmotifcha(Integer decmotifcha) {
        this.decmotifcha = decmotifcha;
    }

    public Integer getDecmotifrea() {
        return this.decmotifrea;
    }

    public Depart decmotifrea(Integer decmotifrea) {
        this.setDecmotifrea(decmotifrea);
        return this;
    }

    public void setDecmotifrea(Integer decmotifrea) {
        this.decmotifrea = decmotifrea;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Depart)) {
            return false;
        }
        return id != null && id.equals(((Depart) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Depart{" +
            "id=" + getId() +
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
