package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Bordereau} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BordereauDTO implements Serializable {

    private String id;

    @NotNull
    private Integer deccent;

    @NotNull
    private Integer decagenc;

    @NotNull
    private Integer exercice;

    @NotNull
    private Integer denbord;

    @NotNull
    private Integer det_deccent;

    @NotNull
    private Integer det_decagenc;

    @NotNull
    private Integer decserv;

    @NotNull
    private String cdmac;

    @NotNull
    private String decoper;

    @NotNull
    private String decsean;

    @NotNull
    private LocalDate dedated;

    @NotNull
    private Integer denumdp;

    @NotNull
    private Integer matric;

    @NotNull
    private Integer matric1;

    @NotNull
    private String natbord;

    @NotNull
    private LocalDate detadedb;

    @NotNull
    private Integer numb_pdat;

    @NotNull
    private LocalDate deheupsr;

    @NotNull
    private Integer demnttn;

    @NotNull
    private Integer demntto;

    @NotNull
    private String decetbr;

    @NotNull
    private String decetcs;

    @NotNull
    private Integer denumcs;

    @NotNull
    private Integer denumbr;

    @NotNull
    private Integer denumver;

    @NotNull
    private String declote;

    @NotNull
    private LocalDate date_saisie;

    @NotNull
    private String clorec;

    @NotNull
    private Integer demntvers;

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

    public Integer getExercice() {
        return exercice;
    }

    public void setExercice(Integer exercice) {
        this.exercice = exercice;
    }

    public Integer getDenbord() {
        return denbord;
    }

    public void setDenbord(Integer denbord) {
        this.denbord = denbord;
    }

    public Integer getDet_deccent() {
        return det_deccent;
    }

    public void setDet_deccent(Integer det_deccent) {
        this.det_deccent = det_deccent;
    }

    public Integer getDet_decagenc() {
        return det_decagenc;
    }

    public void setDet_decagenc(Integer det_decagenc) {
        this.det_decagenc = det_decagenc;
    }

    public Integer getDecserv() {
        return decserv;
    }

    public void setDecserv(Integer decserv) {
        this.decserv = decserv;
    }

    public String getCdmac() {
        return cdmac;
    }

    public void setCdmac(String cdmac) {
        this.cdmac = cdmac;
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

    public String getNatbord() {
        return natbord;
    }

    public void setNatbord(String natbord) {
        this.natbord = natbord;
    }

    public LocalDate getDetadedb() {
        return detadedb;
    }

    public void setDetadedb(LocalDate detadedb) {
        this.detadedb = detadedb;
    }

    public Integer getNumb_pdat() {
        return numb_pdat;
    }

    public void setNumb_pdat(Integer numb_pdat) {
        this.numb_pdat = numb_pdat;
    }

    public LocalDate getDeheupsr() {
        return deheupsr;
    }

    public void setDeheupsr(LocalDate deheupsr) {
        this.deheupsr = deheupsr;
    }

    public Integer getDemnttn() {
        return demnttn;
    }

    public void setDemnttn(Integer demnttn) {
        this.demnttn = demnttn;
    }

    public Integer getDemntto() {
        return demntto;
    }

    public void setDemntto(Integer demntto) {
        this.demntto = demntto;
    }

    public String getDecetbr() {
        return decetbr;
    }

    public void setDecetbr(String decetbr) {
        this.decetbr = decetbr;
    }

    public String getDecetcs() {
        return decetcs;
    }

    public void setDecetcs(String decetcs) {
        this.decetcs = decetcs;
    }

    public Integer getDenumcs() {
        return denumcs;
    }

    public void setDenumcs(Integer denumcs) {
        this.denumcs = denumcs;
    }

    public Integer getDenumbr() {
        return denumbr;
    }

    public void setDenumbr(Integer denumbr) {
        this.denumbr = denumbr;
    }

    public Integer getDenumver() {
        return denumver;
    }

    public void setDenumver(Integer denumver) {
        this.denumver = denumver;
    }

    public String getDeclote() {
        return declote;
    }

    public void setDeclote(String declote) {
        this.declote = declote;
    }

    public LocalDate getDate_saisie() {
        return date_saisie;
    }

    public void setDate_saisie(LocalDate date_saisie) {
        this.date_saisie = date_saisie;
    }

    public String getClorec() {
        return clorec;
    }

    public void setClorec(String clorec) {
        this.clorec = clorec;
    }

    public Integer getDemntvers() {
        return demntvers;
    }

    public void setDemntvers(Integer demntvers) {
        this.demntvers = demntvers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BordereauDTO)) {
            return false;
        }

        BordereauDTO bordereauDTO = (BordereauDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bordereauDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BordereauDTO{" +
            "id='" + getId() + "'" +
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
