package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Deprotat} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DeprotatDTO implements Serializable {

    private String id;

    @NotNull
    private Integer deccent;

    @NotNull
    private Integer decagenc;

    @NotNull
    private LocalDate dedated;

    @NotNull
    private Integer denumdp;

    @NotNull
    private Integer decserv;

    @NotNull
    private String decoper;

    @NotNull
    private String decsean;

    private Integer numrotat;

    private Integer ligdeccent;

    private Integer ligdecagenc;

    private String denumli;

    private String decstat;

    private String decsta1;

    private Integer matric;

    private Integer matric1;

    private String cdmac;

    private LocalDate hdeparte;

    private LocalDate hretoure;

    private LocalDate harralle;

    private LocalDate harrrete;

    private String rannul;

    private Double km;

    private Integer motifa;

    private String observ;

    private Integer recettesvoy;

    private Integer nbrevoy;

    private Integer paye;

    private Integer cd1;

    private Integer cd2;

    private Integer cd3;

    private Integer decmotifcha;

    private Integer decmotifrea;

    private Integer idapex;

    private String plusmoins;

    private String a;

    private String r;

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

    public Integer getNumrotat() {
        return numrotat;
    }

    public void setNumrotat(Integer numrotat) {
        this.numrotat = numrotat;
    }

    public Integer getLigdeccent() {
        return ligdeccent;
    }

    public void setLigdeccent(Integer ligdeccent) {
        this.ligdeccent = ligdeccent;
    }

    public Integer getLigdecagenc() {
        return ligdecagenc;
    }

    public void setLigdecagenc(Integer ligdecagenc) {
        this.ligdecagenc = ligdecagenc;
    }

    public String getDenumli() {
        return denumli;
    }

    public void setDenumli(String denumli) {
        this.denumli = denumli;
    }

    public String getDecstat() {
        return decstat;
    }

    public void setDecstat(String decstat) {
        this.decstat = decstat;
    }

    public String getDecsta1() {
        return decsta1;
    }

    public void setDecsta1(String decsta1) {
        this.decsta1 = decsta1;
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

    public String getCdmac() {
        return cdmac;
    }

    public void setCdmac(String cdmac) {
        this.cdmac = cdmac;
    }

    public LocalDate getHdeparte() {
        return hdeparte;
    }

    public void setHdeparte(LocalDate hdeparte) {
        this.hdeparte = hdeparte;
    }

    public LocalDate getHretoure() {
        return hretoure;
    }

    public void setHretoure(LocalDate hretoure) {
        this.hretoure = hretoure;
    }

    public LocalDate getHarralle() {
        return harralle;
    }

    public void setHarralle(LocalDate harralle) {
        this.harralle = harralle;
    }

    public LocalDate getHarrrete() {
        return harrrete;
    }

    public void setHarrrete(LocalDate harrrete) {
        this.harrrete = harrrete;
    }

    public String getRannul() {
        return rannul;
    }

    public void setRannul(String rannul) {
        this.rannul = rannul;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public Integer getMotifa() {
        return motifa;
    }

    public void setMotifa(Integer motifa) {
        this.motifa = motifa;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public Integer getRecettesvoy() {
        return recettesvoy;
    }

    public void setRecettesvoy(Integer recettesvoy) {
        this.recettesvoy = recettesvoy;
    }

    public Integer getNbrevoy() {
        return nbrevoy;
    }

    public void setNbrevoy(Integer nbrevoy) {
        this.nbrevoy = nbrevoy;
    }

    public Integer getPaye() {
        return paye;
    }

    public void setPaye(Integer paye) {
        this.paye = paye;
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

    public Integer getIdapex() {
        return idapex;
    }

    public void setIdapex(Integer idapex) {
        this.idapex = idapex;
    }

    public String getPlusmoins() {
        return plusmoins;
    }

    public void setPlusmoins(String plusmoins) {
        this.plusmoins = plusmoins;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeprotatDTO)) {
            return false;
        }

        DeprotatDTO deprotatDTO = (DeprotatDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, deprotatDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeprotatDTO{" +
            "id='" + getId() + "'" +
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
