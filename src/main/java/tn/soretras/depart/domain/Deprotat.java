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
    private String dedated;

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
    private Integer lig_deccent;

    @Field("ligdecagenc")
    private Integer lig_decagenc;

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
    private String hdeparte;

    @Field("hretoure")
    private String hretoure;

    @Field("harralle")
    private String harralle;

    @Field("harrrete")
    private String harrrete;

    @Field("rannul")
    private String r_annul;

    @Field("km")
    private Double km;

    @Field("motifa")
    private Integer motif_a;

    @Field("observ")
    private String observ;

    @Field("recettesvoy")
    private Integer recettes_voy;

    @Field("nbrevoy")
    private Integer nbre_voy;

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
    private Integer id_apex;

    @Field("plusmoins")
    private String plus_moins;

    @Field("a")
    private String a;

    @Field("r")
    private String r;

    @DBRef
    @Field("depart")
    @JsonIgnoreProperties(value = { "deprotats" }, allowSetters = true)
    private Depart depart;

    // jhipster-needle-entity-add-field - JHipster will add fields here

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

    public String getDedated() {
        return this.dedated;
    }

    public Deprotat dedated(String dedated) {
        this.setDedated(dedated);
        return this;
    }

    public void setDedated(String dedated) {
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

    public Integer getLig_deccent() {
        return this.lig_deccent;
    }

    public Deprotat lig_deccent(Integer lig_deccent) {
        this.setLig_deccent(lig_deccent);
        return this;
    }

    public void setLig_deccent(Integer lig_deccent) {
        this.lig_deccent = lig_deccent;
    }

    public Integer getLig_decagenc() {
        return this.lig_decagenc;
    }

    public Deprotat lig_decagenc(Integer lig_decagenc) {
        this.setLig_decagenc(lig_decagenc);
        return this;
    }

    public void setLig_decagenc(Integer lig_decagenc) {
        this.lig_decagenc = lig_decagenc;
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

    public String getHdeparte() {
        return this.hdeparte;
    }

    public Deprotat hdeparte(String hdeparte) {
        this.setHdeparte(hdeparte);
        return this;
    }

    public void setHdeparte(String hdeparte) {
        this.hdeparte = hdeparte;
    }

    public String getHretoure() {
        return this.hretoure;
    }

    public Deprotat hretoure(String hretoure) {
        this.setHretoure(hretoure);
        return this;
    }

    public void setHretoure(String hretoure) {
        this.hretoure = hretoure;
    }

    public String getHarralle() {
        return this.harralle;
    }

    public Deprotat harralle(String harralle) {
        this.setHarralle(harralle);
        return this;
    }

    public void setHarralle(String harralle) {
        this.harralle = harralle;
    }

    public String getHarrrete() {
        return this.harrrete;
    }

    public Deprotat harrrete(String harrrete) {
        this.setHarrrete(harrrete);
        return this;
    }

    public void setHarrrete(String harrrete) {
        this.harrrete = harrrete;
    }

    public String getR_annul() {
        return this.r_annul;
    }

    public Deprotat r_annul(String r_annul) {
        this.setR_annul(r_annul);
        return this;
    }

    public void setR_annul(String r_annul) {
        this.r_annul = r_annul;
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

    public Integer getMotif_a() {
        return this.motif_a;
    }

    public Deprotat motif_a(Integer motif_a) {
        this.setMotif_a(motif_a);
        return this;
    }

    public void setMotif_a(Integer motif_a) {
        this.motif_a = motif_a;
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

    public Integer getRecettes_voy() {
        return this.recettes_voy;
    }

    public Deprotat recettes_voy(Integer recettes_voy) {
        this.setRecettes_voy(recettes_voy);
        return this;
    }

    public void setRecettes_voy(Integer recettes_voy) {
        this.recettes_voy = recettes_voy;
    }

    public Integer getNbre_voy() {
        return this.nbre_voy;
    }

    public Deprotat nbre_voy(Integer nbre_voy) {
        this.setNbre_voy(nbre_voy);
        return this;
    }

    public void setNbre_voy(Integer nbre_voy) {
        this.nbre_voy = nbre_voy;
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

    public Integer getId_apex() {
        return this.id_apex;
    }

    public Deprotat id_apex(Integer id_apex) {
        this.setId_apex(id_apex);
        return this;
    }

    public void setId_apex(Integer id_apex) {
        this.id_apex = id_apex;
    }

    public String getPlus_moins() {
        return this.plus_moins;
    }

    public Deprotat plus_moins(String plus_moins) {
        this.setPlus_moins(plus_moins);
        return this;
    }

    public void setPlus_moins(String plus_moins) {
        this.plus_moins = plus_moins;
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
            ", ligdeccent=" + getLig_deccent() +
            ", ligdecagenc=" + getLig_decagenc() +
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
            ", rannul='" + getR_annul() + "'" +
            ", km=" + getKm() +
            ", motifa=" + getMotif_a() +
            ", observ='" + getObserv() + "'" +
            ", recettesvoy=" + getRecettes_voy() +
            ", nbrevoy=" + getNbre_voy() +
            ", paye=" + getPaye() +
            ", cd1=" + getCd1() +
            ", cd2=" + getCd2() +
            ", cd3=" + getCd3() +
            ", decmotifcha=" + getDecmotifcha() +
            ", decmotifrea=" + getDecmotifrea() +
            ", idapex=" + getId_apex() +
            ", plusmoins='" + getPlus_moins() + "'" +
            ", a='" + getA() + "'" +
            ", r='" + getR() + "'" +
            "}";
    }
}
