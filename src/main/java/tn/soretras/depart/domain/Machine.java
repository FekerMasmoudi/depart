package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Machine.
 */
@Document(collection = "machine")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Machine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("cdmac")
    private String cdmac;

    @Field("cdmod")
    private String cdmod;

    @Field("cdmarque")
    private String cdmarque;

    @Field("lbmac")
    private String lbmac;

    @Field("refmac")
    private String ref_mac;

    @Field("serie")
    private String serie;

    @Field("datfab")
    private String dat_fab;

    @Field("datacq")
    private String dat_acq;

    @Field("datmes")
    private String dat_mes;

    @Field("valacq")
    private Integer val_acq;

    @Field("obs")
    private String obs;

    @Field("numplan")
    private String numplan;

    @Field("cdlipro")
    private String cdlipro;

    @Field("immat")
    private String immat;

    @Field("marque")
    private String marque;

    @Field("typev")
    private String type_v;

    @Field("numser")
    private String num_ser;

    @Field("puiss")
    private String puiss;

    @Field("nrj")
    private String nrj;

    @Field("genre")
    private String genre;

    @Field("cylind")
    private Integer cylind;

    @Field("pdsvid")
    private Integer pds_vid;

    @Field("charge")
    private Integer charge;

    @Field("plcass")
    private Integer plc_ass;

    @Field("plcdeb")
    private Integer plc_deb;

    @Field("cpt")
    private Integer cpt;

    @Field("cptmnt")
    private Integer cpt_mnt;

    @Field("actif")
    private Integer actif;

    @Field("datact")
    private String dat_act;

    @Field("cdcatvh")
    private String cdcatvh;

    @Field("taux")
    private Integer taux;

    @Field("kmmoy")
    private Double km_moy;

    @Field("codstat")
    private Integer codstat;

    @Field("edition")
    private String edition;

    @Field("valassur")
    private Integer val_assur;

    @Field("valamort")
    private Integer val_amort;

    @Field("consommodel")
    private Integer consom_model;

    @Field("decetat")
    private String decetat;

    @Field("codtypvoit")
    private String codtypvoit;

    @Field("cdtyp")
    private String cdtyp;

    @Field("cdnat")
    private Integer cdnat;

    @Field("typbv")
    private String typbv;

    @Field("cdtypbv")
    private String cdtypbv;

    @Field("pneu")
    private String pneu;

    @Field("gps")
    private String gps;

    @Field("marquebv")
    private String marque_bv;

    @Field("typboite")
    private String typ_boite;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Machine() {}

    public Machine id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCdmac() {
        return this.cdmac;
    }

    public Machine cdmac(String cdmac) {
        this.setCdmac(cdmac);
        return this;
    }

    public void setCdmac(String cdmac) {
        this.cdmac = cdmac;
    }

    public String getCdmod() {
        return this.cdmod;
    }

    public Machine cdmod(String cdmod) {
        this.setCdmod(cdmod);
        return this;
    }

    public void setCdmod(String cdmod) {
        this.cdmod = cdmod;
    }

    public String getCdmarque() {
        return this.cdmarque;
    }

    public Machine cdmarque(String cdmarque) {
        this.setCdmarque(cdmarque);
        return this;
    }

    public void setCdmarque(String cdmarque) {
        this.cdmarque = cdmarque;
    }

    public String getLbmac() {
        return this.lbmac;
    }

    public Machine lbmac(String lbmac) {
        this.setLbmac(lbmac);
        return this;
    }

    public void setLbmac(String lbmac) {
        this.lbmac = lbmac;
    }

    public String getRef_mac() {
        return this.ref_mac;
    }

    public Machine ref_mac(String ref_mac) {
        this.setRef_mac(ref_mac);
        return this;
    }

    public void setRef_mac(String ref_mac) {
        this.ref_mac = ref_mac;
    }

    public String getSerie() {
        return this.serie;
    }

    public Machine serie(String serie) {
        this.setSerie(serie);
        return this;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDat_fab() {
        return this.dat_fab;
    }

    public Machine dat_fab(String dat_fab) {
        this.setDat_fab(dat_fab);
        return this;
    }

    public void setDat_fab(String dat_fab) {
        this.dat_fab = dat_fab;
    }

    public String getDat_acq() {
        return this.dat_acq;
    }

    public Machine dat_acq(String dat_acq) {
        this.setDat_acq(dat_acq);
        return this;
    }

    public void setDat_acq(String dat_acq) {
        this.dat_acq = dat_acq;
    }

    public String getDat_mes() {
        return this.dat_mes;
    }

    public Machine dat_mes(String dat_mes) {
        this.setDat_mes(dat_mes);
        return this;
    }

    public void setDat_mes(String dat_mes) {
        this.dat_mes = dat_mes;
    }

    public Integer getVal_acq() {
        return this.val_acq;
    }

    public Machine val_acq(Integer val_acq) {
        this.setVal_acq(val_acq);
        return this;
    }

    public void setVal_acq(Integer val_acq) {
        this.val_acq = val_acq;
    }

    public String getObs() {
        return this.obs;
    }

    public Machine obs(String obs) {
        this.setObs(obs);
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getNumplan() {
        return this.numplan;
    }

    public Machine numplan(String numplan) {
        this.setNumplan(numplan);
        return this;
    }

    public void setNumplan(String numplan) {
        this.numplan = numplan;
    }

    public String getCdlipro() {
        return this.cdlipro;
    }

    public Machine cdlipro(String cdlipro) {
        this.setCdlipro(cdlipro);
        return this;
    }

    public void setCdlipro(String cdlipro) {
        this.cdlipro = cdlipro;
    }

    public String getImmat() {
        return this.immat;
    }

    public Machine immat(String immat) {
        this.setImmat(immat);
        return this;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

    public String getMarque() {
        return this.marque;
    }

    public Machine marque(String marque) {
        this.setMarque(marque);
        return this;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getType_v() {
        return this.type_v;
    }

    public Machine type_v(String type_v) {
        this.setType_v(type_v);
        return this;
    }

    public void setType_v(String type_v) {
        this.type_v = type_v;
    }

    public String getNum_ser() {
        return this.num_ser;
    }

    public Machine num_ser(String num_ser) {
        this.setNum_ser(num_ser);
        return this;
    }

    public void setNum_ser(String num_ser) {
        this.num_ser = num_ser;
    }

    public String getPuiss() {
        return this.puiss;
    }

    public Machine puiss(String puiss) {
        this.setPuiss(puiss);
        return this;
    }

    public void setPuiss(String puiss) {
        this.puiss = puiss;
    }

    public String getNrj() {
        return this.nrj;
    }

    public Machine nrj(String nrj) {
        this.setNrj(nrj);
        return this;
    }

    public void setNrj(String nrj) {
        this.nrj = nrj;
    }

    public String getGenre() {
        return this.genre;
    }

    public Machine genre(String genre) {
        this.setGenre(genre);
        return this;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getCylind() {
        return this.cylind;
    }

    public Machine cylind(Integer cylind) {
        this.setCylind(cylind);
        return this;
    }

    public void setCylind(Integer cylind) {
        this.cylind = cylind;
    }

    public Integer getPds_vid() {
        return this.pds_vid;
    }

    public Machine pds_vid(Integer pds_vid) {
        this.setPds_vid(pds_vid);
        return this;
    }

    public void setPds_vid(Integer pds_vid) {
        this.pds_vid = pds_vid;
    }

    public Integer getCharge() {
        return this.charge;
    }

    public Machine charge(Integer charge) {
        this.setCharge(charge);
        return this;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public Integer getPlc_ass() {
        return this.plc_ass;
    }

    public Machine plc_ass(Integer plc_ass) {
        this.setPlc_ass(plc_ass);
        return this;
    }

    public void setPlc_ass(Integer plc_ass) {
        this.plc_ass = plc_ass;
    }

    public Integer getPlc_deb() {
        return this.plc_deb;
    }

    public Machine plc_deb(Integer plc_deb) {
        this.setPlc_deb(plc_deb);
        return this;
    }

    public void setPlc_deb(Integer plc_deb) {
        this.plc_deb = plc_deb;
    }

    public Integer getCpt() {
        return this.cpt;
    }

    public Machine cpt(Integer cpt) {
        this.setCpt(cpt);
        return this;
    }

    public void setCpt(Integer cpt) {
        this.cpt = cpt;
    }

    public Integer getCpt_mnt() {
        return this.cpt_mnt;
    }

    public Machine cpt_mnt(Integer cpt_mnt) {
        this.setCpt_mnt(cpt_mnt);
        return this;
    }

    public void setCpt_mnt(Integer cpt_mnt) {
        this.cpt_mnt = cpt_mnt;
    }

    public Integer getActif() {
        return this.actif;
    }

    public Machine actif(Integer actif) {
        this.setActif(actif);
        return this;
    }

    public void setActif(Integer actif) {
        this.actif = actif;
    }

    public String getDat_act() {
        return this.dat_act;
    }

    public Machine dat_act(String dat_act) {
        this.setDat_acq(dat_act);
        return this;
    }

    public void setDat_act(String dat_act) {
        this.dat_act = dat_act;
    }

    public String getCdcatvh() {
        return this.cdcatvh;
    }

    public Machine cdcatvh(String cdcatvh) {
        this.setCdcatvh(cdcatvh);
        return this;
    }

    public void setCdcatvh(String cdcatvh) {
        this.cdcatvh = cdcatvh;
    }

    public Integer getTaux() {
        return this.taux;
    }

    public Machine taux(Integer taux) {
        this.setTaux(taux);
        return this;
    }

    public void setTaux(Integer taux) {
        this.taux = taux;
    }

    public Double getKm_moy() {
        return this.km_moy;
    }

    public Machine km_moy(Double km_moy) {
        this.setKm_moy(km_moy);
        return this;
    }

    public void setKm_moy(Double km_moy) {
        this.km_moy = km_moy;
    }

    public Integer getCodstat() {
        return this.codstat;
    }

    public Machine codstat(Integer codstat) {
        this.setCodstat(codstat);
        return this;
    }

    public void setCodstat(Integer codstat) {
        this.codstat = codstat;
    }

    public String getEdition() {
        return this.edition;
    }

    public Machine edition(String edition) {
        this.setEdition(edition);
        return this;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getVal_assur() {
        return this.val_assur;
    }

    public Machine val_assur(Integer val_assur) {
        this.setVal_assur(val_assur);
        return this;
    }

    public void setVal_assur(Integer val_assur) {
        this.val_assur = val_assur;
    }

    public Integer getVal_amort() {
        return this.val_amort;
    }

    public Machine val_amort(Integer val_amort) {
        this.setVal_amort(val_amort);
        return this;
    }

    public void setVal_amort(Integer val_amort) {
        this.val_amort = val_amort;
    }

    public Integer getConsom_model() {
        return this.consom_model;
    }

    public Machine consom_model(Integer consom_model) {
        this.setConsom_model(consom_model);
        return this;
    }

    public void setConsom_model(Integer consom_model) {
        this.consom_model = consom_model;
    }

    public String getDecetat() {
        return this.decetat;
    }

    public Machine decetat(String decetat) {
        this.setDecetat(decetat);
        return this;
    }

    public void setDecetat(String decetat) {
        this.decetat = decetat;
    }

    public String getCodtypvoit() {
        return this.codtypvoit;
    }

    public Machine codtypvoit(String codtypvoit) {
        this.setCodtypvoit(codtypvoit);
        return this;
    }

    public void setCodtypvoit(String codtypvoit) {
        this.codtypvoit = codtypvoit;
    }

    public String getCdtyp() {
        return this.cdtyp;
    }

    public Machine cdtyp(String cdtyp) {
        this.setCdtyp(cdtyp);
        return this;
    }

    public void setCdtyp(String cdtyp) {
        this.cdtyp = cdtyp;
    }

    public Integer getCdnat() {
        return this.cdnat;
    }

    public Machine cdnat(Integer cdnat) {
        this.setCdnat(cdnat);
        return this;
    }

    public void setCdnat(Integer cdnat) {
        this.cdnat = cdnat;
    }

    public String getTypbv() {
        return this.typbv;
    }

    public Machine typbv(String typbv) {
        this.setTypbv(typbv);
        return this;
    }

    public void setTypbv(String typbv) {
        this.typbv = typbv;
    }

    public String getCdtypbv() {
        return this.cdtypbv;
    }

    public Machine cdtypbv(String cdtypbv) {
        this.setCdtypbv(cdtypbv);
        return this;
    }

    public void setCdtypbv(String cdtypbv) {
        this.cdtypbv = cdtypbv;
    }

    public String getPneu() {
        return this.pneu;
    }

    public Machine pneu(String pneu) {
        this.setPneu(pneu);
        return this;
    }

    public void setPneu(String pneu) {
        this.pneu = pneu;
    }

    public String getGps() {
        return this.gps;
    }

    public Machine gps(String gps) {
        this.setGps(gps);
        return this;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getMarque_bv() {
        return this.marque_bv;
    }

    public Machine marque_bv(String marque_bv) {
        this.setMarque_bv(marque_bv);
        return this;
    }

    public void setMarque_bv(String marque_bv) {
        this.marque_bv = marque_bv;
    }

    public String getTyp_boite() {
        return this.typ_boite;
    }

    public Machine typ_boite(String typ_boite) {
        this.setTyp_boite(typ_boite);
        return this;
    }

    public void setTyp_boite(String typ_boite) {
        this.typ_boite = typ_boite;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Machine)) {
            return false;
        }
        return id != null && id.equals(((Machine) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Machine{" +
            "id=" + getId() +
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
