package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
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
    private String dedated;

    @NotNull
    private Integer denumdp;

    @NotNull
    private Integer decserv;

    @NotNull
    private String decoper;

    @NotNull
    private String decsean;

    private Integer numrotat;

    private Integer lig_deccent;

    private Integer lig_decagenc;

    private String denumli;

    private String decstat;

    private String decsta1;

    private Integer matric;

    private Integer matric1;

    private String cdmac;

    private String hdeparte;

    private String hretoure;

    private String harralle;

    private String harrrete;

    private String r_annul;

    private Double km;

    private Integer motif_a;

    private String observ;

    private Integer recettes_voy;

    private Integer nbre_voy;

    private Integer paye;

    private Integer cd1;

    private Integer cd2;

    private Integer cd3;

    private Integer decmotifcha;

    private Integer decmotifrea;

    private Integer id_apex;

    private String plus_moins;

    private String a;

    private String r;

    private DepartDTO depart;

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

    public Integer getLig_deccent() {
        return lig_deccent;
    }

    public void setLig_deccent(Integer lig_deccent) {
        this.lig_deccent = lig_deccent;
    }

    public Integer getLig_decagenc() {
        return lig_decagenc;
    }

    public void setLig_decagenc(Integer lig_decagenc) {
        this.lig_decagenc = lig_decagenc;
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

    public String getHdeparte() {
        return hdeparte;
    }

    public void setHdeparte(String hdeparte) {
        this.hdeparte = hdeparte;
    }

    public String getHretoure() {
        return hretoure;
    }

    public void setHretoure(String hretoure) {
        this.hretoure = hretoure;
    }

    public String getHarralle() {
        return harralle;
    }

    public void setHarralle(String harralle) {
        this.harralle = harralle;
    }

    public String getHarrrete() {
        return harrrete;
    }

    public void setHarrrete(String harrrete) {
        this.harrrete = harrrete;
    }

    public String getR_annul() {
        return r_annul;
    }

    public void set_Rannul(String r_annul) {
        this.r_annul = r_annul;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public Integer getMotif_a() {
        return motif_a;
    }

    public void setMotif_a(Integer motif_a) {
        this.motif_a = motif_a;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public Integer getRecettes_voy() {
        return recettes_voy;
    }

    public void setRecettes_voy(Integer recettes_voy) {
        this.recettes_voy = recettes_voy;
    }

    public Integer getNbre_voy() {
        return nbre_voy;
    }

    public void setNbre_voy(Integer nbre_voy) {
        this.nbre_voy = nbre_voy;
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

    public Integer getId_apex() {
        return id_apex;
    }

    public void setId_apex(Integer id_apex) {
        this.id_apex = id_apex;
    }

    public String getPlus_moins() {
        return plus_moins;
    }

    public void setPlus_moins(String plus_moins) {
        this.plus_moins = plus_moins;
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

    public DepartDTO getDepart() {
        return depart;
    }

    public void setDepart(DepartDTO depart) {
        this.depart = depart;
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
