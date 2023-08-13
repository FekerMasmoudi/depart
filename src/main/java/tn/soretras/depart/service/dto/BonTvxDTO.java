package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.BonTvx} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BonTvxDTO implements Serializable {

    private String id;

    private Integer cdexerc;

    private Integer numbt;

    private String cdtier;

    private String cdmac;

    private String maccdmac;

    private String cdserv;

    private Integer decagen;

    private Integer dradecagen;

    private String cdorga;

    private String refbt;

    private LocalDate datbt;

    private LocalDate datdt;

    private LocalDate datft;

    private String vld;

    private String typtvx;

    private LocalDate heurdb;

    private LocalDate heurfi;

    private String observ;

    private LocalDate datsrt;

    private LocalDate heursr;

    private String obstest;

    private Integer indexdep;

    private Integer indexarr;

    private String immatex;

    private String nomchauff;

    private String numpermis;

    private String etab;

    private Integer compteur;

    private Integer cptorg;

    private String cdtyptr;

    private String decstat;

    private Integer testeur;

    private String motifdep;

    private String cdtypmnt;

    private LocalDate datsorprev;

    private LocalDate datmnqdu;

    private LocalDate datmnqau;

    private LocalDate datentant;

    private String codstat;

    private LocalDate datvld;

    private String observ1;

    private Integer testeur1;

    private Integer validag;

    private LocalDate datsais;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCdexerc() {
        return cdexerc;
    }

    public void setCdexerc(Integer cdexerc) {
        this.cdexerc = cdexerc;
    }

    public Integer getNumbt() {
        return numbt;
    }

    public void setNumbt(Integer numbt) {
        this.numbt = numbt;
    }

    public String getCdtier() {
        return cdtier;
    }

    public void setCdtier(String cdtier) {
        this.cdtier = cdtier;
    }

    public String getCdmac() {
        return cdmac;
    }

    public void setCdmac(String cdmac) {
        this.cdmac = cdmac;
    }

    public String getMaccdmac() {
        return maccdmac;
    }

    public void setMaccdmac(String maccdmac) {
        this.maccdmac = maccdmac;
    }

    public String getCdserv() {
        return cdserv;
    }

    public void setCdserv(String cdserv) {
        this.cdserv = cdserv;
    }

    public Integer getDecagen() {
        return decagen;
    }

    public void setDecagen(Integer decagen) {
        this.decagen = decagen;
    }

    public Integer getDradecagen() {
        return dradecagen;
    }

    public void setDradecagen(Integer dradecagen) {
        this.dradecagen = dradecagen;
    }

    public String getCdorga() {
        return cdorga;
    }

    public void setCdorga(String cdorga) {
        this.cdorga = cdorga;
    }

    public String getRefbt() {
        return refbt;
    }

    public void setRefbt(String refbt) {
        this.refbt = refbt;
    }

    public LocalDate getDatbt() {
        return datbt;
    }

    public void setDatbt(LocalDate datbt) {
        this.datbt = datbt;
    }

    public LocalDate getDatdt() {
        return datdt;
    }

    public void setDatdt(LocalDate datdt) {
        this.datdt = datdt;
    }

    public LocalDate getDatft() {
        return datft;
    }

    public void setDatft(LocalDate datft) {
        this.datft = datft;
    }

    public String getVld() {
        return vld;
    }

    public void setVld(String vld) {
        this.vld = vld;
    }

    public String getTyptvx() {
        return typtvx;
    }

    public void setTyptvx(String typtvx) {
        this.typtvx = typtvx;
    }

    public LocalDate getHeurdb() {
        return heurdb;
    }

    public void setHeurdb(LocalDate heurdb) {
        this.heurdb = heurdb;
    }

    public LocalDate getHeurfi() {
        return heurfi;
    }

    public void setHeurfi(LocalDate heurfi) {
        this.heurfi = heurfi;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public LocalDate getDatsrt() {
        return datsrt;
    }

    public void setDatsrt(LocalDate datsrt) {
        this.datsrt = datsrt;
    }

    public LocalDate getHeursr() {
        return heursr;
    }

    public void setHeursr(LocalDate heursr) {
        this.heursr = heursr;
    }

    public String getObstest() {
        return obstest;
    }

    public void setObstest(String obstest) {
        this.obstest = obstest;
    }

    public Integer getIndexdep() {
        return indexdep;
    }

    public void setIndexdep(Integer indexdep) {
        this.indexdep = indexdep;
    }

    public Integer getIndexarr() {
        return indexarr;
    }

    public void setIndexarr(Integer indexarr) {
        this.indexarr = indexarr;
    }

    public String getImmatex() {
        return immatex;
    }

    public void setImmatex(String immatex) {
        this.immatex = immatex;
    }

    public String getNomchauff() {
        return nomchauff;
    }

    public void setNomchauff(String nomchauff) {
        this.nomchauff = nomchauff;
    }

    public String getNumpermis() {
        return numpermis;
    }

    public void setNumpermis(String numpermis) {
        this.numpermis = numpermis;
    }

    public String getEtab() {
        return etab;
    }

    public void setEtab(String etab) {
        this.etab = etab;
    }

    public Integer getCompteur() {
        return compteur;
    }

    public void setCompteur(Integer compteur) {
        this.compteur = compteur;
    }

    public Integer getCptorg() {
        return cptorg;
    }

    public void setCptorg(Integer cptorg) {
        this.cptorg = cptorg;
    }

    public String getCdtyptr() {
        return cdtyptr;
    }

    public void setCdtyptr(String cdtyptr) {
        this.cdtyptr = cdtyptr;
    }

    public String getDecstat() {
        return decstat;
    }

    public void setDecstat(String decstat) {
        this.decstat = decstat;
    }

    public Integer getTesteur() {
        return testeur;
    }

    public void setTesteur(Integer testeur) {
        this.testeur = testeur;
    }

    public String getMotifdep() {
        return motifdep;
    }

    public void setMotifdep(String motifdep) {
        this.motifdep = motifdep;
    }

    public String getCdtypmnt() {
        return cdtypmnt;
    }

    public void setCdtypmnt(String cdtypmnt) {
        this.cdtypmnt = cdtypmnt;
    }

    public LocalDate getDatsorprev() {
        return datsorprev;
    }

    public void setDatsorprev(LocalDate datsorprev) {
        this.datsorprev = datsorprev;
    }

    public LocalDate getDatmnqdu() {
        return datmnqdu;
    }

    public void setDatmnqdu(LocalDate datmnqdu) {
        this.datmnqdu = datmnqdu;
    }

    public LocalDate getDatmnqau() {
        return datmnqau;
    }

    public void setDatmnqau(LocalDate datmnqau) {
        this.datmnqau = datmnqau;
    }

    public LocalDate getDatentant() {
        return datentant;
    }

    public void setDatentant(LocalDate datentant) {
        this.datentant = datentant;
    }

    public String getCodstat() {
        return codstat;
    }

    public void setCodstat(String codstat) {
        this.codstat = codstat;
    }

    public LocalDate getDatvld() {
        return datvld;
    }

    public void setDatvld(LocalDate datvld) {
        this.datvld = datvld;
    }

    public String getObserv1() {
        return observ1;
    }

    public void setObserv1(String observ1) {
        this.observ1 = observ1;
    }

    public Integer getTesteur1() {
        return testeur1;
    }

    public void setTesteur1(Integer testeur1) {
        this.testeur1 = testeur1;
    }

    public Integer getValidag() {
        return validag;
    }

    public void setValidag(Integer validag) {
        this.validag = validag;
    }

    public LocalDate getDatsais() {
        return datsais;
    }

    public void setDatsais(LocalDate datsais) {
        this.datsais = datsais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BonTvxDTO)) {
            return false;
        }

        BonTvxDTO bonTvxDTO = (BonTvxDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bonTvxDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BonTvxDTO{" +
            "id='" + getId() + "'" +
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
