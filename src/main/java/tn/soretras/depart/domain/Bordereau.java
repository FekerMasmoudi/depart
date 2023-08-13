package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Bordereau.
 */
@Document(collection = "bordereau")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Bordereau implements Serializable {

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
    @Field("exercice")
    private Integer exercice;

    @NotNull
    @Field("denbord")
    private Integer denbord;

    @NotNull
    @Field("det_deccent")
    private Integer det_deccent;

    @NotNull
    @Field("det_decagenc")
    private Integer det_decagenc;

    @NotNull
    @Field("decserv")
    private Integer decserv;

    @NotNull
    @Field("cdmac")
    private String cdmac;

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

    @NotNull
    @Field("matric")
    private Integer matric;

    @NotNull
    @Field("matric_1")
    private Integer matric1;

    @NotNull
    @Field("natbord")
    private String natbord;

    @NotNull
    @Field("detadedb")
    private LocalDate detadedb;

    @NotNull
    @Field("numb_pdat")
    private Integer numb_pdat;

    @NotNull
    @Field("deheupsr")
    private LocalDate deheupsr;

    @NotNull
    @Field("demnttn")
    private Integer demnttn;

    @NotNull
    @Field("demntto")
    private Integer demntto;

    @NotNull
    @Field("decetbr")
    private String decetbr;

    @NotNull
    @Field("decetcs")
    private String decetcs;

    @NotNull
    @Field("denumcs")
    private Integer denumcs;

    @NotNull
    @Field("denumbr")
    private Integer denumbr;

    @NotNull
    @Field("denumver")
    private Integer denumver;

    @NotNull
    @Field("declote")
    private String declote;

    @NotNull
    @Field("date_saisie")
    private LocalDate date_saisie;

    @NotNull
    @Field("clorec")
    private String clorec;

    @NotNull
    @Field("demntvers")
    private Integer demntvers;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Bordereau id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public Bordereau deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public Bordereau decagenc(Integer decagenc) {
        this.setDecagenc(decagenc);
        return this;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    public Integer getExercice() {
        return this.exercice;
    }

    public Bordereau exercice(Integer exercice) {
        this.setExercice(exercice);
        return this;
    }

    public void setExercice(Integer exercice) {
        this.exercice = exercice;
    }

    public Integer getDenbord() {
        return this.denbord;
    }

    public Bordereau denbord(Integer denbord) {
        this.setDenbord(denbord);
        return this;
    }

    public void setDenbord(Integer denbord) {
        this.denbord = denbord;
    }

    public Integer getDet_deccent() {
        return this.det_deccent;
    }

    public Bordereau det_deccent(Integer det_deccent) {
        this.setDet_deccent(det_deccent);
        return this;
    }

    public void setDet_deccent(Integer det_deccent) {
        this.det_deccent = det_deccent;
    }

    public Integer getDet_decagenc() {
        return this.det_decagenc;
    }

    public Bordereau det_decagenc(Integer det_decagenc) {
        this.setDet_decagenc(det_decagenc);
        return this;
    }

    public void setDet_decagenc(Integer det_decagenc) {
        this.det_decagenc = det_decagenc;
    }

    public Integer getDecserv() {
        return this.decserv;
    }

    public Bordereau decserv(Integer decserv) {
        this.setDecserv(decserv);
        return this;
    }

    public void setDecserv(Integer decserv) {
        this.decserv = decserv;
    }

    public String getCdmac() {
        return this.cdmac;
    }

    public Bordereau cdmac(String cdmac) {
        this.setCdmac(cdmac);
        return this;
    }

    public void setCdmac(String cdmac) {
        this.cdmac = cdmac;
    }

    public String getDecoper() {
        return this.decoper;
    }

    public Bordereau decoper(String decoper) {
        this.setDecoper(decoper);
        return this;
    }

    public void setDecoper(String decoper) {
        this.decoper = decoper;
    }

    public String getDecsean() {
        return this.decsean;
    }

    public Bordereau decsean(String decsean) {
        this.setDecsean(decsean);
        return this;
    }

    public void setDecsean(String decsean) {
        this.decsean = decsean;
    }

    public LocalDate getDedated() {
        return this.dedated;
    }

    public Bordereau dedated(LocalDate dedated) {
        this.setDedated(dedated);
        return this;
    }

    public void setDedated(LocalDate dedated) {
        this.dedated = dedated;
    }

    public Integer getDenumdp() {
        return this.denumdp;
    }

    public Bordereau denumdp(Integer denumdp) {
        this.setDenumdp(denumdp);
        return this;
    }

    public void setDenumdp(Integer denumdp) {
        this.denumdp = denumdp;
    }

    public Integer getMatric() {
        return this.matric;
    }

    public Bordereau matric(Integer matric) {
        this.setMatric(matric);
        return this;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public Integer getMatric1() {
        return this.matric1;
    }

    public Bordereau matric1(Integer matric1) {
        this.setMatric1(matric1);
        return this;
    }

    public void setMatric1(Integer matric1) {
        this.matric1 = matric1;
    }

    public String getNatbord() {
        return this.natbord;
    }

    public Bordereau natbord(String natbord) {
        this.setNatbord(natbord);
        return this;
    }

    public void setNatbord(String natbord) {
        this.natbord = natbord;
    }

    public LocalDate getDetadedb() {
        return this.detadedb;
    }

    public Bordereau detadedb(LocalDate detadedb) {
        this.setDetadedb(detadedb);
        return this;
    }

    public void setDetadedb(LocalDate detadedb) {
        this.detadedb = detadedb;
    }

    public Integer getNumb_pdat() {
        return this.numb_pdat;
    }

    public Bordereau numb_pdat(Integer numb_pdat) {
        this.setNumb_pdat(numb_pdat);
        return this;
    }

    public void setNumb_pdat(Integer numb_pdat) {
        this.numb_pdat = numb_pdat;
    }

    public LocalDate getDeheupsr() {
        return this.deheupsr;
    }

    public Bordereau deheupsr(LocalDate deheupsr) {
        this.setDeheupsr(deheupsr);
        return this;
    }

    public void setDeheupsr(LocalDate deheupsr) {
        this.deheupsr = deheupsr;
    }

    public Integer getDemnttn() {
        return this.demnttn;
    }

    public Bordereau demnttn(Integer demnttn) {
        this.setDemnttn(demnttn);
        return this;
    }

    public void setDemnttn(Integer demnttn) {
        this.demnttn = demnttn;
    }

    public Integer getDemntto() {
        return this.demntto;
    }

    public Bordereau demntto(Integer demntto) {
        this.setDemntto(demntto);
        return this;
    }

    public void setDemntto(Integer demntto) {
        this.demntto = demntto;
    }

    public String getDecetbr() {
        return this.decetbr;
    }

    public Bordereau decetbr(String decetbr) {
        this.setDecetbr(decetbr);
        return this;
    }

    public void setDecetbr(String decetbr) {
        this.decetbr = decetbr;
    }

    public String getDecetcs() {
        return this.decetcs;
    }

    public Bordereau decetcs(String decetcs) {
        this.setDecetcs(decetcs);
        return this;
    }

    public void setDecetcs(String decetcs) {
        this.decetcs = decetcs;
    }

    public Integer getDenumcs() {
        return this.denumcs;
    }

    public Bordereau denumcs(Integer denumcs) {
        this.setDenumcs(denumcs);
        return this;
    }

    public void setDenumcs(Integer denumcs) {
        this.denumcs = denumcs;
    }

    public Integer getDenumbr() {
        return this.denumbr;
    }

    public Bordereau denumbr(Integer denumbr) {
        this.setDenumbr(denumbr);
        return this;
    }

    public void setDenumbr(Integer denumbr) {
        this.denumbr = denumbr;
    }

    public Integer getDenumver() {
        return this.denumver;
    }

    public Bordereau denumver(Integer denumver) {
        this.setDenumver(denumver);
        return this;
    }

    public void setDenumver(Integer denumver) {
        this.denumver = denumver;
    }

    public String getDeclote() {
        return this.declote;
    }

    public Bordereau declote(String declote) {
        this.setDeclote(declote);
        return this;
    }

    public void setDeclote(String declote) {
        this.declote = declote;
    }

    public LocalDate getDate_saisie() {
        return this.date_saisie;
    }

    public Bordereau date_saisie(LocalDate date_saisie) {
        this.setDate_saisie(date_saisie);
        return this;
    }

    public void setDate_saisie(LocalDate date_saisie) {
        this.date_saisie = date_saisie;
    }

    public String getClorec() {
        return this.clorec;
    }

    public Bordereau clorec(String clorec) {
        this.setClorec(clorec);
        return this;
    }

    public void setClorec(String clorec) {
        this.clorec = clorec;
    }

    public Integer getDemntvers() {
        return this.demntvers;
    }

    public Bordereau demntvers(Integer demntvers) {
        this.setDemntvers(demntvers);
        return this;
    }

    public void setDemntvers(Integer demntvers) {
        this.demntvers = demntvers;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bordereau)) {
            return false;
        }
        return id != null && id.equals(((Bordereau) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bordereau{" +
            "id=" + getId() +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", exercice=" + getExercice() +
            ", denbord=" + getDenbord() +
            ", det_deccent=" + getDet_deccent() +
            ", det_decagenc=" + getDet_decagenc() +
            ", decserv=" + getDecserv() +
            ", cdmac='" + getCdmac() + "'" +
            ", decoper='" + getDecoper() + "'" +
            ", decsean='" + getDecsean() + "'" +
            ", dedated='" + getDedated() + "'" +
            ", denumdp=" + getDenumdp() +
            ", matric=" + getMatric() +
            ", matric1=" + getMatric1() +
            ", natbord='" + getNatbord() + "'" +
            ", detadedb='" + getDetadedb() + "'" +
            ", numb_pdat=" + getNumb_pdat() +
            ", deheupsr='" + getDeheupsr() + "'" +
            ", demnttn=" + getDemnttn() +
            ", demntto=" + getDemntto() +
            ", decetbr='" + getDecetbr() + "'" +
            ", decetcs='" + getDecetcs() + "'" +
            ", denumcs=" + getDenumcs() +
            ", denumbr=" + getDenumbr() +
            ", denumver=" + getDenumver() +
            ", declote='" + getDeclote() + "'" +
            ", date_saisie='" + getDate_saisie() + "'" +
            ", clorec='" + getClorec() + "'" +
            ", demntvers=" + getDemntvers() +
            "}";
    }
}
