package tn.soretras.depart.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    private String dedated;

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
    private String deheups;

    @Field("deheufs")
    private String deheufs;

    @Field("denbrro")
    private Integer denbrro;

    @Field("deheuaa")
    private String deheuaa;

    @Field("deheudr")
    private String deheudr;

    @Field("deheupd")
    private String deheupd;

    @Field("deampli")
    private String deampli;

    @Field("obsind")
    private String obs_ind;

    @Field("vldroul")
    private String vld_roul;

    @Field("deetat")
    private String deetat;

    @Field("deannul")
    private String deannul;

    @Field("decclot")
    private String decclot;

    @Field("execute")
    private String execute;

    @Field("motifa")
    private String motif_a;

    @Field("observ")
    private String observ;

    @Field("recettes")
    private Float recettes;

    @Field("nbrevoy")
    private Integer nbre_voy;

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

    @Field("idapex")
    private Integer id_apex;

    @DBRef
    @Field("deprotat")
    @JsonIgnoreProperties(value = { "depart" }, allowSetters = true)
    private Set<Deprotat> deprotats = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Depart() {}

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

    public String getDedated() {
        return this.dedated;
    }

    public Depart dedated(String dedated) {
        this.setDedated(dedated);
        return this;
    }

    public void setDedated(String dedated) {
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

    public String getDeheups() {
        return this.deheups;
    }

    public Depart deheups(String deheups) {
        this.setDeheups(deheups);
        return this;
    }

    public void setDeheups(String deheups) {
        this.deheups = deheups;
    }

    public String getDeheufs() {
        return this.deheufs;
    }

    public Depart deheufs(String deheufs) {
        this.setDeheufs(deheufs);
        return this;
    }

    public void setDeheufs(String deheufs) {
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

    public String getDeheuaa() {
        return this.deheuaa;
    }

    public Depart deheuaa(String deheuaa) {
        this.setDeheuaa(deheuaa);
        return this;
    }

    public void setDeheuaa(String deheuaa) {
        this.deheuaa = deheuaa;
    }

    public String getDeheudr() {
        return this.deheudr;
    }

    public Depart deheudr(String deheudr) {
        this.setDeheudr(deheudr);
        return this;
    }

    public void setDeheudr(String deheudr) {
        this.deheudr = deheudr;
    }

    public String getDeheupd() {
        return this.deheupd;
    }

    public Depart deheupd(String deheupd) {
        this.setDeheupd(deheupd);
        return this;
    }

    public void setDeheupd(String deheupd) {
        this.deheupd = deheupd;
    }

    public String getDeampli() {
        return this.deampli;
    }

    public Depart deampli(String deampli) {
        this.setDeampli(deampli);
        return this;
    }

    public void setDeampli(String deampli) {
        this.deampli = deampli;
    }

    public String getObs_ind() {
        return this.obs_ind;
    }

    public Depart obs_ind(String obs_ind) {
        this.setObs_ind(obs_ind);
        return this;
    }

    public void setObs_ind(String obs_ind) {
        this.obs_ind = obs_ind;
    }

    public String getVld_roul() {
        return this.vld_roul;
    }

    public Depart vld_roul(String vld_roul) {
        this.setVld_roul(vld_roul);
        return this;
    }

    public void setVld_roul(String vld_roul) {
        this.vld_roul = vld_roul;
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

    public String getMotif_a() {
        return this.motif_a;
    }

    public Depart motif_a(String motif_a) {
        this.setMotif_a(motif_a);
        return this;
    }

    public void setMotif_a(String motif_a) {
        this.motif_a = motif_a;
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

    public Integer getNbre_voy() {
        return this.nbre_voy;
    }

    public Depart nbre_voy(Integer nbre_voy) {
        this.setNbre_voy(nbre_voy);
        return this;
    }

    public void setNbre_voy(Integer nbre_voy) {
        this.nbre_voy = nbre_voy;
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

    public void setId_apex(Integer id_apex) {
        this.id_apex = id_apex;
    }

    public Integer getId_apex() {
        return this.id_apex;
    }

    public Depart id_apex(Integer id_apex) {
        this.setId_apex(id_apex);
        return this;
    }

    public Set<Deprotat> getDeprotats() {
        return this.deprotats;
    }

    public void setDeprotats(Set<Deprotat> deprotats) {
        if (this.deprotats != null) {
            this.deprotats.forEach(i -> i.setDepart(null));
        }
        if (deprotats != null) {
            deprotats.forEach(i -> i.setDepart(this));
        }
        this.deprotats = deprotats;
    }

    public Depart deprotats(Set<Deprotat> deprotats) {
        this.setDeprotats(deprotats);
        return this;
    }

    public Depart addDeprotat(Deprotat deprotat) {
        this.deprotats.add(deprotat);
        deprotat.setDepart(this);
        return this;
    }

    public Depart removeDeprotat(Deprotat deprotat) {
        this.deprotats.remove(deprotat);
        deprotat.setDepart(null);
        return this;
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
            "}";
    }
}
