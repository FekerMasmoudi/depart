package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A BonTvx.
 */
@Document(collection = "bon_tvx")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BonTvx implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("cdexerc")
    private Integer cdexerc;

    @Field("numbt")
    private Integer numbt;

    @Field("cdtier")
    private String cdtier;

    @Field("cdmac")
    private String cdmac;

    @Field("maccdmac")
    private String maccdmac;

    @Field("cdserv")
    private String cdserv;

    @Field("decagen")
    private Integer decagen;

    @Field("dradecagen")
    private Integer dradecagen;

    @Field("cdorga")
    private String cdorga;

    @Field("refbt")
    private String refbt;

    @Field("datbt")
    private LocalDate datbt;

    @Field("datdt")
    private LocalDate datdt;

    @Field("datft")
    private LocalDate datft;

    @Field("vld")
    private String vld;

    @Field("typtvx")
    private String typtvx;

    @Field("heurdb")
    private LocalDate heurdb;

    @Field("heurfi")
    private LocalDate heurfi;

    @Field("observ")
    private String observ;

    @Field("datsrt")
    private LocalDate datsrt;

    @Field("heursr")
    private LocalDate heursr;

    @Field("obstest")
    private String obstest;

    @Field("indexdep")
    private Integer indexdep;

    @Field("indexarr")
    private Integer indexarr;

    @Field("immatex")
    private String immatex;

    @Field("nomchauff")
    private String nomchauff;

    @Field("numpermis")
    private String numpermis;

    @Field("etab")
    private String etab;

    @Field("compteur")
    private Integer compteur;

    @Field("cptorg")
    private Integer cptorg;

    @Field("cdtyptr")
    private String cdtyptr;

    @Field("decstat")
    private String decstat;

    @Field("testeur")
    private Integer testeur;

    @Field("motifdep")
    private String motifdep;

    @Field("cdtypmnt")
    private String cdtypmnt;

    @Field("datsorprev")
    private LocalDate datsorprev;

    @Field("datmnqdu")
    private LocalDate datmnqdu;

    @Field("datmnqau")
    private LocalDate datmnqau;

    @Field("datentant")
    private LocalDate datentant;

    @Field("codstat")
    private String codstat;

    @Field("datvld")
    private LocalDate datvld;

    @Field("observ_1")
    private String observ1;

    @Field("testeur_1")
    private Integer testeur1;

    @Field("validag")
    private Integer validag;

    @Field("datsais")
    private LocalDate datsais;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public BonTvx id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCdexerc() {
        return this.cdexerc;
    }

    public BonTvx cdexerc(Integer cdexerc) {
        this.setCdexerc(cdexerc);
        return this;
    }

    public void setCdexerc(Integer cdexerc) {
        this.cdexerc = cdexerc;
    }

    public Integer getNumbt() {
        return this.numbt;
    }

    public BonTvx numbt(Integer numbt) {
        this.setNumbt(numbt);
        return this;
    }

    public void setNumbt(Integer numbt) {
        this.numbt = numbt;
    }

    public String getCdtier() {
        return this.cdtier;
    }

    public BonTvx cdtier(String cdtier) {
        this.setCdtier(cdtier);
        return this;
    }

    public void setCdtier(String cdtier) {
        this.cdtier = cdtier;
    }

    public String getCdmac() {
        return this.cdmac;
    }

    public BonTvx cdmac(String cdmac) {
        this.setCdmac(cdmac);
        return this;
    }

    public void setCdmac(String cdmac) {
        this.cdmac = cdmac;
    }

    public String getMaccdmac() {
        return this.maccdmac;
    }

    public BonTvx maccdmac(String maccdmac) {
        this.setMaccdmac(maccdmac);
        return this;
    }

    public void setMaccdmac(String maccdmac) {
        this.maccdmac = maccdmac;
    }

    public String getCdserv() {
        return this.cdserv;
    }

    public BonTvx cdserv(String cdserv) {
        this.setCdserv(cdserv);
        return this;
    }

    public void setCdserv(String cdserv) {
        this.cdserv = cdserv;
    }

    public Integer getDecagen() {
        return this.decagen;
    }

    public BonTvx decagen(Integer decagen) {
        this.setDecagen(decagen);
        return this;
    }

    public void setDecagen(Integer decagen) {
        this.decagen = decagen;
    }

    public Integer getDradecagen() {
        return this.dradecagen;
    }

    public BonTvx dradecagen(Integer dradecagen) {
        this.setDradecagen(dradecagen);
        return this;
    }

    public void setDradecagen(Integer dradecagen) {
        this.dradecagen = dradecagen;
    }

    public String getCdorga() {
        return this.cdorga;
    }

    public BonTvx cdorga(String cdorga) {
        this.setCdorga(cdorga);
        return this;
    }

    public void setCdorga(String cdorga) {
        this.cdorga = cdorga;
    }

    public String getRefbt() {
        return this.refbt;
    }

    public BonTvx refbt(String refbt) {
        this.setRefbt(refbt);
        return this;
    }

    public void setRefbt(String refbt) {
        this.refbt = refbt;
    }

    public LocalDate getDatbt() {
        return this.datbt;
    }

    public BonTvx datbt(LocalDate datbt) {
        this.setDatbt(datbt);
        return this;
    }

    public void setDatbt(LocalDate datbt) {
        this.datbt = datbt;
    }

    public LocalDate getDatdt() {
        return this.datdt;
    }

    public BonTvx datdt(LocalDate datdt) {
        this.setDatdt(datdt);
        return this;
    }

    public void setDatdt(LocalDate datdt) {
        this.datdt = datdt;
    }

    public LocalDate getDatft() {
        return this.datft;
    }

    public BonTvx datft(LocalDate datft) {
        this.setDatft(datft);
        return this;
    }

    public void setDatft(LocalDate datft) {
        this.datft = datft;
    }

    public String getVld() {
        return this.vld;
    }

    public BonTvx vld(String vld) {
        this.setVld(vld);
        return this;
    }

    public void setVld(String vld) {
        this.vld = vld;
    }

    public String getTyptvx() {
        return this.typtvx;
    }

    public BonTvx typtvx(String typtvx) {
        this.setTyptvx(typtvx);
        return this;
    }

    public void setTyptvx(String typtvx) {
        this.typtvx = typtvx;
    }

    public LocalDate getHeurdb() {
        return this.heurdb;
    }

    public BonTvx heurdb(LocalDate heurdb) {
        this.setHeurdb(heurdb);
        return this;
    }

    public void setHeurdb(LocalDate heurdb) {
        this.heurdb = heurdb;
    }

    public LocalDate getHeurfi() {
        return this.heurfi;
    }

    public BonTvx heurfi(LocalDate heurfi) {
        this.setHeurfi(heurfi);
        return this;
    }

    public void setHeurfi(LocalDate heurfi) {
        this.heurfi = heurfi;
    }

    public String getObserv() {
        return this.observ;
    }

    public BonTvx observ(String observ) {
        this.setObserv(observ);
        return this;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public LocalDate getDatsrt() {
        return this.datsrt;
    }

    public BonTvx datsrt(LocalDate datsrt) {
        this.setDatsrt(datsrt);
        return this;
    }

    public void setDatsrt(LocalDate datsrt) {
        this.datsrt = datsrt;
    }

    public LocalDate getHeursr() {
        return this.heursr;
    }

    public BonTvx heursr(LocalDate heursr) {
        this.setHeursr(heursr);
        return this;
    }

    public void setHeursr(LocalDate heursr) {
        this.heursr = heursr;
    }

    public String getObstest() {
        return this.obstest;
    }

    public BonTvx obstest(String obstest) {
        this.setObstest(obstest);
        return this;
    }

    public void setObstest(String obstest) {
        this.obstest = obstest;
    }

    public Integer getIndexdep() {
        return this.indexdep;
    }

    public BonTvx indexdep(Integer indexdep) {
        this.setIndexdep(indexdep);
        return this;
    }

    public void setIndexdep(Integer indexdep) {
        this.indexdep = indexdep;
    }

    public Integer getIndexarr() {
        return this.indexarr;
    }

    public BonTvx indexarr(Integer indexarr) {
        this.setIndexarr(indexarr);
        return this;
    }

    public void setIndexarr(Integer indexarr) {
        this.indexarr = indexarr;
    }

    public String getImmatex() {
        return this.immatex;
    }

    public BonTvx immatex(String immatex) {
        this.setImmatex(immatex);
        return this;
    }

    public void setImmatex(String immatex) {
        this.immatex = immatex;
    }

    public String getNomchauff() {
        return this.nomchauff;
    }

    public BonTvx nomchauff(String nomchauff) {
        this.setNomchauff(nomchauff);
        return this;
    }

    public void setNomchauff(String nomchauff) {
        this.nomchauff = nomchauff;
    }

    public String getNumpermis() {
        return this.numpermis;
    }

    public BonTvx numpermis(String numpermis) {
        this.setNumpermis(numpermis);
        return this;
    }

    public void setNumpermis(String numpermis) {
        this.numpermis = numpermis;
    }

    public String getEtab() {
        return this.etab;
    }

    public BonTvx etab(String etab) {
        this.setEtab(etab);
        return this;
    }

    public void setEtab(String etab) {
        this.etab = etab;
    }

    public Integer getCompteur() {
        return this.compteur;
    }

    public BonTvx compteur(Integer compteur) {
        this.setCompteur(compteur);
        return this;
    }

    public void setCompteur(Integer compteur) {
        this.compteur = compteur;
    }

    public Integer getCptorg() {
        return this.cptorg;
    }

    public BonTvx cptorg(Integer cptorg) {
        this.setCptorg(cptorg);
        return this;
    }

    public void setCptorg(Integer cptorg) {
        this.cptorg = cptorg;
    }

    public String getCdtyptr() {
        return this.cdtyptr;
    }

    public BonTvx cdtyptr(String cdtyptr) {
        this.setCdtyptr(cdtyptr);
        return this;
    }

    public void setCdtyptr(String cdtyptr) {
        this.cdtyptr = cdtyptr;
    }

    public String getDecstat() {
        return this.decstat;
    }

    public BonTvx decstat(String decstat) {
        this.setDecstat(decstat);
        return this;
    }

    public void setDecstat(String decstat) {
        this.decstat = decstat;
    }

    public Integer getTesteur() {
        return this.testeur;
    }

    public BonTvx testeur(Integer testeur) {
        this.setTesteur(testeur);
        return this;
    }

    public void setTesteur(Integer testeur) {
        this.testeur = testeur;
    }

    public String getMotifdep() {
        return this.motifdep;
    }

    public BonTvx motifdep(String motifdep) {
        this.setMotifdep(motifdep);
        return this;
    }

    public void setMotifdep(String motifdep) {
        this.motifdep = motifdep;
    }

    public String getCdtypmnt() {
        return this.cdtypmnt;
    }

    public BonTvx cdtypmnt(String cdtypmnt) {
        this.setCdtypmnt(cdtypmnt);
        return this;
    }

    public void setCdtypmnt(String cdtypmnt) {
        this.cdtypmnt = cdtypmnt;
    }

    public LocalDate getDatsorprev() {
        return this.datsorprev;
    }

    public BonTvx datsorprev(LocalDate datsorprev) {
        this.setDatsorprev(datsorprev);
        return this;
    }

    public void setDatsorprev(LocalDate datsorprev) {
        this.datsorprev = datsorprev;
    }

    public LocalDate getDatmnqdu() {
        return this.datmnqdu;
    }

    public BonTvx datmnqdu(LocalDate datmnqdu) {
        this.setDatmnqdu(datmnqdu);
        return this;
    }

    public void setDatmnqdu(LocalDate datmnqdu) {
        this.datmnqdu = datmnqdu;
    }

    public LocalDate getDatmnqau() {
        return this.datmnqau;
    }

    public BonTvx datmnqau(LocalDate datmnqau) {
        this.setDatmnqau(datmnqau);
        return this;
    }

    public void setDatmnqau(LocalDate datmnqau) {
        this.datmnqau = datmnqau;
    }

    public LocalDate getDatentant() {
        return this.datentant;
    }

    public BonTvx datentant(LocalDate datentant) {
        this.setDatentant(datentant);
        return this;
    }

    public void setDatentant(LocalDate datentant) {
        this.datentant = datentant;
    }

    public String getCodstat() {
        return this.codstat;
    }

    public BonTvx codstat(String codstat) {
        this.setCodstat(codstat);
        return this;
    }

    public void setCodstat(String codstat) {
        this.codstat = codstat;
    }

    public LocalDate getDatvld() {
        return this.datvld;
    }

    public BonTvx datvld(LocalDate datvld) {
        this.setDatvld(datvld);
        return this;
    }

    public void setDatvld(LocalDate datvld) {
        this.datvld = datvld;
    }

    public String getObserv1() {
        return this.observ1;
    }

    public BonTvx observ1(String observ1) {
        this.setObserv1(observ1);
        return this;
    }

    public void setObserv1(String observ1) {
        this.observ1 = observ1;
    }

    public Integer getTesteur1() {
        return this.testeur1;
    }

    public BonTvx testeur1(Integer testeur1) {
        this.setTesteur1(testeur1);
        return this;
    }

    public void setTesteur1(Integer testeur1) {
        this.testeur1 = testeur1;
    }

    public Integer getValidag() {
        return this.validag;
    }

    public BonTvx validag(Integer validag) {
        this.setValidag(validag);
        return this;
    }

    public void setValidag(Integer validag) {
        this.validag = validag;
    }

    public LocalDate getDatsais() {
        return this.datsais;
    }

    public BonTvx datsais(LocalDate datsais) {
        this.setDatsais(datsais);
        return this;
    }

    public void setDatsais(LocalDate datsais) {
        this.datsais = datsais;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BonTvx)) {
            return false;
        }
        return id != null && id.equals(((BonTvx) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BonTvx{" +
            "id=" + getId() +
            ", cdexerc=" + getCdexerc() +
            ", numbt=" + getNumbt() +
            ", cdtier='" + getCdtier() + "'" +
            ", cdmac='" + getCdmac() + "'" +
            ", maccdmac='" + getMaccdmac() + "'" +
            ", cdserv='" + getCdserv() + "'" +
            ", decagen=" + getDecagen() +
            ", dradecagen=" + getDradecagen() +
            ", cdorga='" + getCdorga() + "'" +
            ", refbt='" + getRefbt() + "'" +
            ", datbt='" + getDatbt() + "'" +
            ", datdt='" + getDatdt() + "'" +
            ", datft='" + getDatft() + "'" +
            ", vld='" + getVld() + "'" +
            ", typtvx='" + getTyptvx() + "'" +
            ", heurdb='" + getHeurdb() + "'" +
            ", heurfi='" + getHeurfi() + "'" +
            ", observ='" + getObserv() + "'" +
            ", datsrt='" + getDatsrt() + "'" +
            ", heursr='" + getHeursr() + "'" +
            ", obstest='" + getObstest() + "'" +
            ", indexdep=" + getIndexdep() +
            ", indexarr=" + getIndexarr() +
            ", immatex='" + getImmatex() + "'" +
            ", nomchauff='" + getNomchauff() + "'" +
            ", numpermis='" + getNumpermis() + "'" +
            ", etab='" + getEtab() + "'" +
            ", compteur=" + getCompteur() +
            ", cptorg=" + getCptorg() +
            ", cdtyptr='" + getCdtyptr() + "'" +
            ", decstat='" + getDecstat() + "'" +
            ", testeur=" + getTesteur() +
            ", motifdep='" + getMotifdep() + "'" +
            ", cdtypmnt='" + getCdtypmnt() + "'" +
            ", datsorprev='" + getDatsorprev() + "'" +
            ", datmnqdu='" + getDatmnqdu() + "'" +
            ", datmnqau='" + getDatmnqau() + "'" +
            ", datentant='" + getDatentant() + "'" +
            ", codstat='" + getCodstat() + "'" +
            ", datvld='" + getDatvld() + "'" +
            ", observ1='" + getObserv1() + "'" +
            ", testeur1=" + getTesteur1() +
            ", validag=" + getValidag() +
            ", datsais='" + getDatsais() + "'" +
            "}";
    }
}
