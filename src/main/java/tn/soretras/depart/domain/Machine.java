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
    private String refmac;

    @Field("serie")
    private String serie;

    @Field("datfab")
    private LocalDate datfab;

    @Field("datacq")
    private LocalDate datacq;

    @Field("datmes")
    private LocalDate datmes;

    @Field("valacq")
    private Integer valacq;

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
    private String typev;

    @Field("numser")
    private String numser;

    @Field("puiss")
    private String puiss;

    @Field("nrj")
    private String nrj;

    @Field("genre")
    private String genre;

    @Field("cylind")
    private Integer cylind;

    @Field("pdsvid")
    private Integer pdsvid;

    @Field("charge")
    private Integer charge;

    @Field("plcass")
    private Integer plcass;

    @Field("plcdeb")
    private Integer plcdeb;

    @Field("cpt")
    private Integer cpt;

    @Field("cptmnt")
    private Integer cptmnt;

    @Field("actif")
    private Integer actif;

    @Field("datact")
    private LocalDate datact;

    @Field("cdcatvh")
    private String cdcatvh;

    @Field("taux")
    private Integer taux;

    @Field("kmmoy")
    private Integer kmmoy;

    @Field("codstat")
    private Integer codstat;

    @Field("edition")
    private String edition;

    @Field("valassur")
    private Integer valassur;

    @Field("valamort")
    private Integer valamort;

    @Field("consommodel")
    private Integer consommodel;

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
    private String marquebv;

    @Field("typboite")
    private String typboite;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Machine(
        String id,
        String cdmac,
        String cdmod,
        String cdmarque,
        String lbmac,
        String refmac,
        String serie,
        LocalDate datfab,
        LocalDate datacq,
        LocalDate datmes,
        Integer valacq,
        String obs,
        String numplan,
        String cdlipro,
        String immat,
        String marque,
        String typev,
        String numser,
        String puiss,
        String nrj,
        String genre,
        Integer cylind,
        Integer pdsvid,
        Integer charge,
        Integer plcass,
        Integer plcdeb,
        Integer cpt,
        Integer cptmnt,
        Integer actif,
        LocalDate datact,
        String cdcatvh,
        Integer taux,
        Integer kmmoy,
        Integer codstat,
        String edition,
        Integer valassur,
        Integer valamort,
        Integer consommodel,
        String decetat,
        String codtypvoit,
        String cdtyp,
        Integer cdnat,
        String typbv,
        String cdtypbv,
        String pneu,
        String gps,
        String marquebv,
        String typboite
    ) {
        this.id = id;
        this.cdmac = cdmac;
        this.cdmod = cdmod;
        this.cdmarque = cdmarque;
        this.lbmac = lbmac;
        this.refmac = refmac;
        this.serie = serie;
        this.datfab = datfab;
        this.datacq = datacq;
        this.datmes = datmes;
        this.valacq = valacq;
        this.obs = obs;
        this.numplan = numplan;
        this.cdlipro = cdlipro;
        this.immat = immat;
        this.marque = marque;
        this.typev = typev;
        this.numser = numser;
        this.puiss = puiss;
        this.nrj = nrj;
        this.genre = genre;
        this.cylind = cylind;
        this.pdsvid = pdsvid;
        this.charge = charge;
        this.plcass = plcass;
        this.plcdeb = plcdeb;
        this.cpt = cpt;
        this.cptmnt = cptmnt;
        this.actif = actif;
        this.datact = datact;
        this.cdcatvh = cdcatvh;
        this.taux = taux;
        this.kmmoy = kmmoy;
        this.codstat = codstat;
        this.edition = edition;
        this.valassur = valassur;
        this.valamort = valamort;
        this.consommodel = consommodel;
        this.decetat = decetat;
        this.codtypvoit = codtypvoit;
        this.cdtyp = cdtyp;
        this.cdnat = cdnat;
        this.typbv = typbv;
        this.cdtypbv = cdtypbv;
        this.pneu = pneu;
        this.gps = gps;
        this.marquebv = marquebv;
        this.typboite = typboite;
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

    public String getRefmac() {
        return this.refmac;
    }

    public Machine refmac(String refmac) {
        this.setRefmac(refmac);
        return this;
    }

    public void setRefmac(String refmac) {
        this.refmac = refmac;
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

    public LocalDate getDatfab() {
        return this.datfab;
    }

    public Machine datfab(LocalDate datfab) {
        this.setDatfab(datfab);
        return this;
    }

    public void setDatfab(LocalDate datfab) {
        this.datfab = datfab;
    }

    public LocalDate getDatacq() {
        return this.datacq;
    }

    public Machine datacq(LocalDate datacq) {
        this.setDatacq(datacq);
        return this;
    }

    public void setDatacq(LocalDate datacq) {
        this.datacq = datacq;
    }

    public LocalDate getDatmes() {
        return this.datmes;
    }

    public Machine datmes(LocalDate datmes) {
        this.setDatmes(datmes);
        return this;
    }

    public void setDatmes(LocalDate datmes) {
        this.datmes = datmes;
    }

    public Integer getValacq() {
        return this.valacq;
    }

    public Machine valacq(Integer valacq) {
        this.setValacq(valacq);
        return this;
    }

    public void setValacq(Integer valacq) {
        this.valacq = valacq;
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

    public String getTypev() {
        return this.typev;
    }

    public Machine typev(String typev) {
        this.setTypev(typev);
        return this;
    }

    public void setTypev(String typev) {
        this.typev = typev;
    }

    public String getNumser() {
        return this.numser;
    }

    public Machine numser(String numser) {
        this.setNumser(numser);
        return this;
    }

    public void setNumser(String numser) {
        this.numser = numser;
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

    public Integer getPdsvid() {
        return this.pdsvid;
    }

    public Machine pdsvid(Integer pdsvid) {
        this.setPdsvid(pdsvid);
        return this;
    }

    public void setPdsvid(Integer pdsvid) {
        this.pdsvid = pdsvid;
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

    public Integer getPlcass() {
        return this.plcass;
    }

    public Machine plcass(Integer plcass) {
        this.setPlcass(plcass);
        return this;
    }

    public void setPlcass(Integer plcass) {
        this.plcass = plcass;
    }

    public Integer getPlcdeb() {
        return this.plcdeb;
    }

    public Machine plcdeb(Integer plcdeb) {
        this.setPlcdeb(plcdeb);
        return this;
    }

    public void setPlcdeb(Integer plcdeb) {
        this.plcdeb = plcdeb;
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

    public Integer getCptmnt() {
        return this.cptmnt;
    }

    public Machine cptmnt(Integer cptmnt) {
        this.setCptmnt(cptmnt);
        return this;
    }

    public void setCptmnt(Integer cptmnt) {
        this.cptmnt = cptmnt;
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

    public LocalDate getDatact() {
        return this.datact;
    }

    public Machine datact(LocalDate datact) {
        this.setDatact(datact);
        return this;
    }

    public void setDatact(LocalDate datact) {
        this.datact = datact;
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

    public Integer getKmmoy() {
        return this.kmmoy;
    }

    public Machine kmmoy(Integer kmmoy) {
        this.setKmmoy(kmmoy);
        return this;
    }

    public void setKmmoy(Integer kmmoy) {
        this.kmmoy = kmmoy;
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

    public Integer getValassur() {
        return this.valassur;
    }

    public Machine valassur(Integer valassur) {
        this.setValassur(valassur);
        return this;
    }

    public void setValassur(Integer valassur) {
        this.valassur = valassur;
    }

    public Integer getValamort() {
        return this.valamort;
    }

    public Machine valamort(Integer valamort) {
        this.setValamort(valamort);
        return this;
    }

    public void setValamort(Integer valamort) {
        this.valamort = valamort;
    }

    public Integer getConsommodel() {
        return this.consommodel;
    }

    public Machine consommodel(Integer consommodel) {
        this.setConsommodel(consommodel);
        return this;
    }

    public void setConsommodel(Integer consommodel) {
        this.consommodel = consommodel;
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

    public String getMarquebv() {
        return this.marquebv;
    }

    public Machine marquebv(String marquebv) {
        this.setMarquebv(marquebv);
        return this;
    }

    public void setMarquebv(String marquebv) {
        this.marquebv = marquebv;
    }

    public String getTypboite() {
        return this.typboite;
    }

    public Machine typboite(String typboite) {
        this.setTypboite(typboite);
        return this;
    }

    public void setTypboite(String typboite) {
        this.typboite = typboite;
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
