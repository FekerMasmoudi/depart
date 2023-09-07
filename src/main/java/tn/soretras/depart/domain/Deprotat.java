package tn.soretras.depart.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Deprotat.
 */
@Document(collection = "deprotat")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Deprotat implements Serializable {

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
    @Field("dedated")
    private LocalDate dedated;

    @NotNull
    @Field("denumdp")
    private Integer denumdp;

    @NotNull
    @Field("decserv")
    private Integer decserv;

    @NotNull
    @Field("decoper")
    private String decoper;

    @NotNull
    @Field("decsean")
    private String decsean;

    @Field("numrotat")
    private Integer numrotat;

    @Field("ligdeccent")
    private Integer ligdeccent;

    @Field("ligdecagenc")
    private Integer ligdecagenc;

    @Field("denumli")
    private String denumli;

    @Field("decstat")
    private String decstat;

    @Field("decsta_1")
    private String decsta1;

    @Field("matric")
    private Integer matric;

    @Field("matric_1")
    private Integer matric1;

    @Field("cdmac")
    private String cdmac;

    @Field("hdeparte")
    private LocalDate hdeparte;

    @Field("hretoure")
    private LocalDate hretoure;

    @Field("harralle")
    private LocalDate harralle;

    @Field("harrrete")
    private LocalDate harrrete;

    @Field("rannul")
    private String rannul;

    @Field("km")
    private Double km;

    @Field("motifa")
    private Integer motifa;

    @Field("observ")
    private String observ;

    @Field("recettesvoy")
    private Integer recettesvoy;

    @Field("nbrevoy")
    private Integer nbrevoy;

    @Field("paye")
    private Integer paye;

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
    private Integer idapex;

    @Field("plusmoins")
    private String plusmoins;

    @Field("a")
    private String a;

    @Field("r")
    private String r;

    @DBRef
    @Field("depart")
    @JsonIgnoreProperties(value = { "deprotats" }, allowSetters = true)
    private Depart depart;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Deprotat(
        String id,
        @NotNull Integer deccent,
        @NotNull Integer decagenc,
        @NotNull LocalDate dedated,
        @NotNull Integer denumdp,
        @NotNull Integer decserv,
        @NotNull String decoper,
        @NotNull String decsean,
        Integer numrotat,
        Integer ligdeccent,
        Integer ligdecagenc,
        String denumli,
        String decstat,
        String decsta1,
        Integer matric,
        Integer matric1,
        String cdmac,
        LocalDate hdeparte,
        LocalDate hretoure,
        LocalDate harralle,
        LocalDate harrrete,
        String rannul,
        Double km,
        Integer motifa,
        String observ,
        Integer recettesvoy,
        Integer nbrevoy,
        Integer paye,
        Integer cd1,
        Integer cd2,
        Integer cd3,
        Integer decmotifcha,
        Integer decmotifrea,
        Integer idapex,
        String plusmoins,
        String a,
        String r
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
        this.ligdeccent = ligdeccent;
        this.ligdecagenc = ligdecagenc;
        this.denumli = denumli;
        this.decstat = decstat;
        this.decsta1 = decsta1;
        this.matric = matric;
        this.matric1 = matric1;
        this.cdmac = cdmac;
        this.hdeparte = hdeparte;
        this.hretoure = hretoure;
        this.harralle = harralle;
        this.harrrete = harrrete;
        this.rannul = rannul;
        this.km = km;
        this.motifa = motifa;
        this.observ = observ;
        this.recettesvoy = recettesvoy;
        this.nbrevoy = nbrevoy;
        this.paye = paye;
        this.cd1 = cd1;
        this.cd2 = cd2;
        this.cd3 = cd3;
        this.decmotifcha = decmotifcha;
        this.decmotifrea = decmotifrea;
        this.idapex = idapex;
        this.plusmoins = plusmoins;
        this.a = a;
        this.r = r;
    }

    public Deprotat() {}

    public String getId() {
        return this.id;
    }

    public Deprotat id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public Deprotat deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public Deprotat decagenc(Integer decagenc) {
        this.setDecagenc(decagenc);
        return this;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    public LocalDate getDedated() {
        return this.dedated;
    }

    public Deprotat dedated(LocalDate dedated) {
        this.setDedated(dedated);
        return this;
    }

    public void setDedated(LocalDate dedated) {
        this.dedated = dedated;
    }

    public Integer getDenumdp() {
        return this.denumdp;
    }

    public Deprotat denumdp(Integer denumdp) {
        this.setDenumdp(denumdp);
        return this;
    }

    public void setDenumdp(Integer denumdp) {
        this.denumdp = denumdp;
    }

    public Integer getDecserv() {
        return this.decserv;
    }

    public Deprotat decserv(Integer decserv) {
        this.setDecserv(decserv);
        return this;
    }

    public void setDecserv(Integer decserv) {
        this.decserv = decserv;
    }

    public String getDecoper() {
        return this.decoper;
    }

    public Deprotat decoper(String decoper) {
        this.setDecoper(decoper);
        return this;
    }

    public void setDecoper(String decoper) {
        this.decoper = decoper;
    }

    public String getDecsean() {
        return this.decsean;
    }

    public Deprotat decsean(String decsean) {
        this.setDecsean(decsean);
        return this;
    }

    public void setDecsean(String decsean) {
        this.decsean = decsean;
    }

    public Integer getNumrotat() {
        return this.numrotat;
    }

    public Deprotat numrotat(Integer numrotat) {
        this.setNumrotat(numrotat);
        return this;
    }

    public void setNumrotat(Integer numrotat) {
        this.numrotat = numrotat;
    }

    public Integer getLigdeccent() {
        return this.ligdeccent;
    }

    public Deprotat ligdeccent(Integer ligdeccent) {
        this.setLigdeccent(ligdeccent);
        return this;
    }

    public void setLigdeccent(Integer ligdeccent) {
        this.ligdeccent = ligdeccent;
    }

    public Integer getLigdecagenc() {
        return this.ligdecagenc;
    }

    public Deprotat ligdecagenc(Integer ligdecagenc) {
        this.setLigdecagenc(ligdecagenc);
        return this;
    }

    public void setLigdecagenc(Integer ligdecagenc) {
        this.ligdecagenc = ligdecagenc;
    }

    public String getDenumli() {
        return this.denumli;
    }

    public Deprotat denumli(String denumli) {
        this.setDenumli(denumli);
        return this;
    }

    public void setDenumli(String denumli) {
        this.denumli = denumli;
    }

    public String getDecstat() {
        return this.decstat;
    }

    public Deprotat decstat(String decstat) {
        this.setDecstat(decstat);
        return this;
    }

    public void setDecstat(String decstat) {
        this.decstat = decstat;
    }

    public String getDecsta1() {
        return this.decsta1;
    }

    public Deprotat decsta1(String decsta1) {
        this.setDecsta1(decsta1);
        return this;
    }

    public void setDecsta1(String decsta1) {
        this.decsta1 = decsta1;
    }

    public Integer getMatric() {
        return this.matric;
    }

    public Deprotat matric(Integer matric) {
        this.setMatric(matric);
        return this;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public Integer getMatric1() {
        return this.matric1;
    }

    public Deprotat matric1(Integer matric1) {
        this.setMatric1(matric1);
        return this;
    }

    public void setMatric1(Integer matric1) {
        this.matric1 = matric1;
    }

    public String getCdmac() {
        return this.cdmac;
    }

    public Deprotat cdmac(String cdmac) {
        this.setCdmac(cdmac);
        return this;
    }

    public void setCdmac(String cdmac) {
        this.cdmac = cdmac;
    }

    public LocalDate getHdeparte() {
        return this.hdeparte;
    }

    public Deprotat hdeparte(LocalDate hdeparte) {
        this.setHdeparte(hdeparte);
        return this;
    }

    public void setHdeparte(LocalDate hdeparte) {
        this.hdeparte = hdeparte;
    }

    public LocalDate getHretoure() {
        return this.hretoure;
    }

    public Deprotat hretoure(LocalDate hretoure) {
        this.setHretoure(hretoure);
        return this;
    }

    public void setHretoure(LocalDate hretoure) {
        this.hretoure = hretoure;
    }

    public LocalDate getHarralle() {
        return this.harralle;
    }

    public Deprotat harralle(LocalDate harralle) {
        this.setHarralle(harralle);
        return this;
    }

    public void setHarralle(LocalDate harralle) {
        this.harralle = harralle;
    }

    public LocalDate getHarrrete() {
        return this.harrrete;
    }

    public Deprotat harrrete(LocalDate harrrete) {
        this.setHarrrete(harrrete);
        return this;
    }

    public void setHarrrete(LocalDate harrrete) {
        this.harrrete = harrrete;
    }

    public String getRannul() {
        return this.rannul;
    }

    public Deprotat rannul(String rannul) {
        this.setRannul(rannul);
        return this;
    }

    public void setRannul(String rannul) {
        this.rannul = rannul;
    }

    public Double getKm() {
        return this.km;
    }

    public Deprotat km(Double km) {
        this.setKm(km);
        return this;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public Integer getMotifa() {
        return this.motifa;
    }

    public Deprotat motifa(Integer motifa) {
        this.setMotifa(motifa);
        return this;
    }

    public void setMotifa(Integer motifa) {
        this.motifa = motifa;
    }

    public String getObserv() {
        return this.observ;
    }

    public Deprotat observ(String observ) {
        this.setObserv(observ);
        return this;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public Integer getRecettesvoy() {
        return this.recettesvoy;
    }

    public Deprotat recettesvoy(Integer recettesvoy) {
        this.setRecettesvoy(recettesvoy);
        return this;
    }

    public void setRecettesvoy(Integer recettesvoy) {
        this.recettesvoy = recettesvoy;
    }

    public Integer getNbrevoy() {
        return this.nbrevoy;
    }

    public Deprotat nbrevoy(Integer nbrevoy) {
        this.setNbrevoy(nbrevoy);
        return this;
    }

    public void setNbrevoy(Integer nbrevoy) {
        this.nbrevoy = nbrevoy;
    }

    public Integer getPaye() {
        return this.paye;
    }

    public Deprotat paye(Integer paye) {
        this.setPaye(paye);
        return this;
    }

    public void setPaye(Integer paye) {
        this.paye = paye;
    }

    public Integer getCd1() {
        return this.cd1;
    }

    public Deprotat cd1(Integer cd1) {
        this.setCd1(cd1);
        return this;
    }

    public void setCd1(Integer cd1) {
        this.cd1 = cd1;
    }

    public Integer getCd2() {
        return this.cd2;
    }

    public Deprotat cd2(Integer cd2) {
        this.setCd2(cd2);
        return this;
    }

    public void setCd2(Integer cd2) {
        this.cd2 = cd2;
    }

    public Integer getCd3() {
        return this.cd3;
    }

    public Deprotat cd3(Integer cd3) {
        this.setCd3(cd3);
        return this;
    }

    public void setCd3(Integer cd3) {
        this.cd3 = cd3;
    }

    public Integer getDecmotifcha() {
        return this.decmotifcha;
    }

    public Deprotat decmotifcha(Integer decmotifcha) {
        this.setDecmotifcha(decmotifcha);
        return this;
    }

    public void setDecmotifcha(Integer decmotifcha) {
        this.decmotifcha = decmotifcha;
    }

    public Integer getDecmotifrea() {
        return this.decmotifrea;
    }

    public Deprotat decmotifrea(Integer decmotifrea) {
        this.setDecmotifrea(decmotifrea);
        return this;
    }

    public void setDecmotifrea(Integer decmotifrea) {
        this.decmotifrea = decmotifrea;
    }

    public Integer getIdapex() {
        return this.idapex;
    }

    public Deprotat idapex(Integer idapex) {
        this.setIdapex(idapex);
        return this;
    }

    public void setIdapex(Integer idapex) {
        this.idapex = idapex;
    }

    public String getPlusmoins() {
        return this.plusmoins;
    }

    public Deprotat plusmoins(String plusmoins) {
        this.setPlusmoins(plusmoins);
        return this;
    }

    public void setPlusmoins(String plusmoins) {
        this.plusmoins = plusmoins;
    }

    public String getA() {
        return this.a;
    }

    public Deprotat a(String a) {
        this.setA(a);
        return this;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getR() {
        return this.r;
    }

    public Deprotat r(String r) {
        this.setR(r);
        return this;
    }

    public void setR(String r) {
        this.r = r;
    }

    public Depart getDepart() {
        return this.depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    public Deprotat depart(Depart depart) {
        this.setDepart(depart);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deprotat)) {
            return false;
        }
        return id != null && id.equals(((Deprotat) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Deprotat{" +
            "id=" + getId() +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", dedated='" + getDedated() + "'" +
            ", denumdp=" + getDenumdp() +
            ", decserv=" + getDecserv() +
            ", decoper='" + getDecoper() + "'" +
            ", decsean='" + getDecsean() + "'" +
            ", numrotat=" + getNumrotat() +
            ", ligdeccent=" + getLigdeccent() +
            ", ligdecagenc=" + getLigdecagenc() +
            ", denumli='" + getDenumli() + "'" +
            ", decstat='" + getDecstat() + "'" +
            ", decsta1='" + getDecsta1() + "'" +
            ", matric=" + getMatric() +
            ", matric1=" + getMatric1() +
            ", cdmac='" + getCdmac() + "'" +
            ", hdeparte='" + getHdeparte() + "'" +
            ", hretoure='" + getHretoure() + "'" +
            ", harralle='" + getHarralle() + "'" +
            ", harrrete='" + getHarrrete() + "'" +
            ", rannul='" + getRannul() + "'" +
            ", km=" + getKm() +
            ", motifa=" + getMotifa() +
            ", observ='" + getObserv() + "'" +
            ", recettesvoy=" + getRecettesvoy() +
            ", nbrevoy=" + getNbrevoy() +
            ", paye=" + getPaye() +
            ", cd1=" + getCd1() +
            ", cd2=" + getCd2() +
            ", cd3=" + getCd3() +
            ", decmotifcha=" + getDecmotifcha() +
            ", decmotifrea=" + getDecmotifrea() +
            ", idapex=" + getIdapex() +
            ", plusmoins='" + getPlusmoins() + "'" +
            ", a='" + getA() + "'" +
            ", r='" + getR() + "'" +
            "}";
    }
}
