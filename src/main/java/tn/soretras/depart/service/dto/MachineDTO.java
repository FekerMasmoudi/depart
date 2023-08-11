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

    private String refmac;

    private String serie;

    private LocalDate datfab;

    private LocalDate datacq;

    private LocalDate datmes;

    private Integer valacq;

    private String obs;

    private String numplan;

    private String cdlipro;

    private String immat;

    private String marque;

    private String typev;

    private String numser;

    private String puiss;

    private String nrj;

    private String genre;

    private Integer cylind;

    private Integer pdsvid;

    private Integer charge;

    private Integer plcass;

    private Integer plcdeb;

    private Integer cpt;

    private Integer cptmnt;

    private Integer actif;

    private LocalDate datact;

    private String cdcatvh;

    private Integer taux;

    private Integer kmmoy;

    private Integer codstat;

    private String edition;

    private Integer valassur;

    private Integer valamort;

    private Integer consommodel;

    private String decetat;

    private String codtypvoit;

    private String cdtyp;

    private Integer cdnat;

    private String typbv;

    private String cdtypbv;

    private String pneu;

    private String gps;

    private String marquebv;

    private String typboite;

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

    public String getRefmac() {
        return refmac;
    }

    public void setRefmac(String refmac) {
        this.refmac = refmac;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public LocalDate getDatfab() {
        return datfab;
    }

    public void setDatfab(LocalDate datfab) {
        this.datfab = datfab;
    }

    public LocalDate getDatacq() {
        return datacq;
    }

    public void setDatacq(LocalDate datacq) {
        this.datacq = datacq;
    }

    public LocalDate getDatmes() {
        return datmes;
    }

    public void setDatmes(LocalDate datmes) {
        this.datmes = datmes;
    }

    public Integer getValacq() {
        return valacq;
    }

    public void setValacq(Integer valacq) {
        this.valacq = valacq;
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

    public String getTypev() {
        return typev;
    }

    public void setTypev(String typev) {
        this.typev = typev;
    }

    public String getNumser() {
        return numser;
    }

    public void setNumser(String numser) {
        this.numser = numser;
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

    public Integer getPdsvid() {
        return pdsvid;
    }

    public void setPdsvid(Integer pdsvid) {
        this.pdsvid = pdsvid;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public Integer getPlcass() {
        return plcass;
    }

    public void setPlcass(Integer plcass) {
        this.plcass = plcass;
    }

    public Integer getPlcdeb() {
        return plcdeb;
    }

    public void setPlcdeb(Integer plcdeb) {
        this.plcdeb = plcdeb;
    }

    public Integer getCpt() {
        return cpt;
    }

    public void setCpt(Integer cpt) {
        this.cpt = cpt;
    }

    public Integer getCptmnt() {
        return cptmnt;
    }

    public void setCptmnt(Integer cptmnt) {
        this.cptmnt = cptmnt;
    }

    public Integer getActif() {
        return actif;
    }

    public void setActif(Integer actif) {
        this.actif = actif;
    }

    public LocalDate getDatact() {
        return datact;
    }

    public void setDatact(LocalDate datact) {
        this.datact = datact;
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

    public Integer getKmmoy() {
        return kmmoy;
    }

    public void setKmmoy(Integer kmmoy) {
        this.kmmoy = kmmoy;
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

    public Integer getValassur() {
        return valassur;
    }

    public void setValassur(Integer valassur) {
        this.valassur = valassur;
    }

    public Integer getValamort() {
        return valamort;
    }

    public void setValamort(Integer valamort) {
        this.valamort = valamort;
    }

    public Integer getConsommodel() {
        return consommodel;
    }

    public void setConsommodel(Integer consommodel) {
        this.consommodel = consommodel;
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

    public String getMarquebv() {
        return marquebv;
    }

    public void setMarquebv(String marquebv) {
        this.marquebv = marquebv;
    }

    public String getTypboite() {
        return typboite;
    }

    public void setTypboite(String typboite) {
        this.typboite = typboite;
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
            ", refmac='" + getRefmac() + "'" +
            ", serie='" + getSerie() + "'" +
            ", datfab='" + getDatfab() + "'" +
            ", datacq='" + getDatacq() + "'" +
            ", datmes='" + getDatmes() + "'" +
            ", valacq=" + getValacq() +
            ", obs='" + getObs() + "'" +
            ", numplan='" + getNumplan() + "'" +
            ", cdlipro='" + getCdlipro() + "'" +
            ", immat='" + getImmat() + "'" +
            ", marque='" + getMarque() + "'" +
            ", typev='" + getTypev() + "'" +
            ", numser='" + getNumser() + "'" +
            ", puiss='" + getPuiss() + "'" +
            ", nrj='" + getNrj() + "'" +
            ", genre='" + getGenre() + "'" +
            ", cylind=" + getCylind() +
            ", pdsvid=" + getPdsvid() +
            ", charge=" + getCharge() +
            ", plcass=" + getPlcass() +
            ", plcdeb=" + getPlcdeb() +
            ", cpt=" + getCpt() +
            ", cptmnt=" + getCptmnt() +
            ", actif=" + getActif() +
            ", datact='" + getDatact() + "'" +
            ", cdcatvh='" + getCdcatvh() + "'" +
            ", taux=" + getTaux() +
            ", kmmoy=" + getKmmoy() +
            ", codstat=" + getCodstat() +
            ", edition='" + getEdition() + "'" +
            ", valassur=" + getValassur() +
            ", valamort=" + getValamort() +
            ", consommodel=" + getConsommodel() +
            ", decetat='" + getDecetat() + "'" +
            ", codtypvoit='" + getCodtypvoit() + "'" +
            ", cdtyp='" + getCdtyp() + "'" +
            ", cdnat=" + getCdnat() +
            ", typbv='" + getTypbv() + "'" +
            ", cdtypbv='" + getCdtypbv() + "'" +
            ", pneu='" + getPneu() + "'" +
            ", gps='" + getGps() + "'" +
            ", marquebv='" + getMarquebv() + "'" +
            ", typboite='" + getTypboite() + "'" +
            "}";
    }
}
