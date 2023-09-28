package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Machine} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MachineDTO implements Serializable {

    private String id;

    private String cdmac;

    private String cdmod;

    private String cdmarque;

    private String lbmac;

    private String ref_mac;

    private String serie;

    private String dat_fab;

    private String dat_acq;

    private String dat_mes;

    private Integer val_acq;

    private String obs;

    private String numplan;

    private String cdlipro;

    private String immat;

    private String marque;

    private String type_v;

    private String num_ser;

    private String puiss;

    private String nrj;

    private String genre;

    private Integer cylind;

    private Integer pds_vid;

    private Integer charge;

    private Integer plc_ass;

    private Integer plc_deb;

    private Integer cpt;

    private Integer cpt_mnt;

    private Integer actif;

    private String dat_act;

    private String cdcatvh;

    private Integer taux;

    private Double km_moy;

    private Integer codstat;

    private String edition;

    private Integer val_assur;

    private Integer val_amort;

    private Integer consom_model;

    private String decetat;

    private String codtypvoit;

    private String cdtyp;

    private Integer cdnat;

    private String typbv;

    private String cdtypbv;

    private String pneu;

    private String gps;

    private String marque_bv;

    private String typ_boite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCdmac() {
        return cdmac;
    }

    public void setCdmac(String cdmac) {
        this.cdmac = cdmac;
    }

    public String getCdmod() {
        return cdmod;
    }

    public void setCdmod(String cdmod) {
        this.cdmod = cdmod;
    }

    public String getCdmarque() {
        return cdmarque;
    }

    public void setCdmarque(String cdmarque) {
        this.cdmarque = cdmarque;
    }

    public String getLbmac() {
        return lbmac;
    }

    public void setLbmac(String lbmac) {
        this.lbmac = lbmac;
    }

    public String getRef_mac() {
        return ref_mac;
    }

    public void setRef_mac(String ref_mac) {
        this.ref_mac = ref_mac;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDat_fab() {
        return dat_fab;
    }

    public void setDat_fab(String dat_fab) {
        this.dat_fab = dat_fab;
    }

    public String getDat_acq() {
        return dat_acq;
    }

    public void setDat_acq(String dat_acq) {
        this.dat_acq = dat_acq;
    }

    public String getDat_mes() {
        return dat_mes;
    }

    public void setDat_mes(String dat_mes) {
        this.dat_mes = dat_mes;
    }

    public Integer getVal_acq() {
        return val_acq;
    }

    public void setVal_acq(Integer val_acq) {
        this.val_acq = val_acq;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getNumplan() {
        return numplan;
    }

    public void setNumplan(String numplan) {
        this.numplan = numplan;
    }

    public String getCdlipro() {
        return cdlipro;
    }

    public void setCdlipro(String cdlipro) {
        this.cdlipro = cdlipro;
    }

    public String getImmat() {
        return immat;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getType_v() {
        return type_v;
    }

    public void setType_v(String type_v) {
        this.type_v = type_v;
    }

    public String getNum_ser() {
        return num_ser;
    }

    public void setNum_ser(String num_ser) {
        this.num_ser = num_ser;
    }

    public String getPuiss() {
        return puiss;
    }

    public void setPuiss(String puiss) {
        this.puiss = puiss;
    }

    public String getNrj() {
        return nrj;
    }

    public void setNrj(String nrj) {
        this.nrj = nrj;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getCylind() {
        return cylind;
    }

    public void setCylind(Integer cylind) {
        this.cylind = cylind;
    }

    public Integer getPds_vid() {
        return pds_vid;
    }

    public void setPds_vid(Integer pds_vid) {
        this.pds_vid = pds_vid;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public Integer getPlc_ass() {
        return plc_ass;
    }

    public void setPlc_ass(Integer plc_ass) {
        this.plc_ass = plc_ass;
    }

    public Integer getPlc_deb() {
        return plc_deb;
    }

    public void setPlc_deb(Integer plc_deb) {
        this.plc_deb = plc_deb;
    }

    public Integer getCpt() {
        return cpt;
    }

    public void setCpt(Integer cpt) {
        this.cpt = cpt;
    }

    public Integer getCpt_mnt() {
        return cpt_mnt;
    }

    public void setCpt_mnt(Integer cpt_mnt) {
        this.cpt_mnt = cpt_mnt;
    }

    public Integer getActif() {
        return actif;
    }

    public void setActif(Integer actif) {
        this.actif = actif;
    }

    public String getDat_act() {
        return dat_act;
    }

    public void setDat_act(String dat_act) {
        this.dat_act = dat_act;
    }

    public String getCdcatvh() {
        return cdcatvh;
    }

    public void setCdcatvh(String cdcatvh) {
        this.cdcatvh = cdcatvh;
    }

    public Integer getTaux() {
        return taux;
    }

    public void setTaux(Integer taux) {
        this.taux = taux;
    }

    public Double getKm_moy() {
        return km_moy;
    }

    public void setKm_moy(Double km_moy) {
        this.km_moy = km_moy;
    }

    public Integer getCodstat() {
        return codstat;
    }

    public void setCodstat(Integer codstat) {
        this.codstat = codstat;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getVal_assur() {
        return val_assur;
    }

    public void setVal_assur(Integer val_assur) {
        this.val_assur = val_assur;
    }

    public Integer getVal_amort() {
        return val_amort;
    }

    public void setVal_amort(Integer val_amort) {
        this.val_amort = val_amort;
    }

    public Integer getConsom_model() {
        return consom_model;
    }

    public void setConsom_model(Integer consom_model) {
        this.consom_model = consom_model;
    }

    public String getDecetat() {
        return decetat;
    }

    public void setDecetat(String decetat) {
        this.decetat = decetat;
    }

    public String getCodtypvoit() {
        return codtypvoit;
    }

    public void setCodtypvoit(String codtypvoit) {
        this.codtypvoit = codtypvoit;
    }

    public String getCdtyp() {
        return cdtyp;
    }

    public void setCdtyp(String cdtyp) {
        this.cdtyp = cdtyp;
    }

    public Integer getCdnat() {
        return cdnat;
    }

    public void setCdnat(Integer cdnat) {
        this.cdnat = cdnat;
    }

    public String getTypbv() {
        return typbv;
    }

    public void setTypbv(String typbv) {
        this.typbv = typbv;
    }

    public String getCdtypbv() {
        return cdtypbv;
    }

    public void setCdtypbv(String cdtypbv) {
        this.cdtypbv = cdtypbv;
    }

    public String getPneu() {
        return pneu;
    }

    public void setPneu(String pneu) {
        this.pneu = pneu;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getMarque_bv() {
        return marque_bv;
    }

    public void setMarque_bv(String marque_bv) {
        this.marque_bv = marque_bv;
    }

    public String getTyp_boite() {
        return typ_boite;
    }

    public void setTyp_boite(String typ_boite) {
        this.typ_boite = typ_boite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MachineDTO)) {
            return false;
        }

        MachineDTO machineDTO = (MachineDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, machineDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MachineDTO{" +
            "id='" + getId() + "'" +
            ", cdmac='" + getCdmac() + "'" +
            ", cdmod='" + getCdmod() + "'" +
            ", cdmarque='" + getCdmarque() + "'" +
            ", lbmac='" + getLbmac() + "'" +
            ", refmac='" + getRef_mac() + "'" +
            ", serie='" + getSerie() + "'" +
            ", datfab='" + getDat_fab() + "'" +
            ", datacq='" + getDat_acq() + "'" +
            ", datmes='" + getDat_mes() + "'" +
            ", valacq=" + getVal_acq() +
            ", obs='" + getObs() + "'" +
            ", numplan='" + getNumplan() + "'" +
            ", cdlipro='" + getCdlipro() + "'" +
            ", immat='" + getImmat() + "'" +
            ", marque='" + getMarque() + "'" +
            ", typev='" + getType_v() + "'" +
            ", numser='" + getNum_ser() + "'" +
            ", puiss='" + getPuiss() + "'" +
            ", nrj='" + getNrj() + "'" +
            ", genre='" + getGenre() + "'" +
            ", cylind=" + getCylind() +
            ", pdsvid=" + getPds_vid() +
            ", charge=" + getCharge() +
            ", plcass=" + getPlc_ass() +
            ", plcdeb=" + getPlc_deb() +
            ", cpt=" + getCpt() +
            ", cptmnt=" + getCpt_mnt() +
            ", actif=" + getActif() +
            ", datact='" + getDat_act() + "'" +
            ", cdcatvh='" + getCdcatvh() + "'" +
            ", taux=" + getTaux() +
            ", kmmoy=" + getKm_moy() +
            ", codstat=" + getCodstat() +
            ", edition='" + getEdition() + "'" +
            ", valassur=" + getVal_assur() +
            ", valamort=" + getVal_amort() +
            ", consommodel=" + getConsom_model() +
            ", decetat='" + getDecetat() + "'" +
            ", codtypvoit='" + getCodtypvoit() + "'" +
            ", cdtyp='" + getCdtyp() + "'" +
            ", cdnat=" + getCdnat() +
            ", typbv='" + getTypbv() + "'" +
            ", cdtypbv='" + getCdtypbv() + "'" +
            ", pneu='" + getPneu() + "'" +
            ", gps='" + getGps() + "'" +
            ", marquebv='" + getMarque_bv() + "'" +
            ", typboite='" + getTyp_boite() + "'" +
            "}";
    }
}
