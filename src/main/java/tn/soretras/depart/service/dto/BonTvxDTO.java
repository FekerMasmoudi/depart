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

    private Integer num_bt;

    private String cdtier;

    private String cdmac;

    private String mac_cdmac;

    private String cdserv;

    private Integer decagen;

    private Integer dra_decagen;

    private String cdorga;

    private String ref_bt;

    private String dat_bt;

    private String dat_dt;

    private String dat_ft;

    private String vld;

    private String typ_tvx;

    private String heurdb;

    private String heurfi;

    private String observ;

    private String dat_srt;

    private String heursr;

    private String obs_test;

    private Integer index_dep;

    private Integer index_arr;

    private String immat_ex;

    private String nom_chauff;

    private String num_permis;

    private String etab;

    private Integer compteur;

    private Integer cpt_org;

    private String cdtyptr;

    private String decstat;

    private Integer testeur;

    private String motif_dep;

    private String cdtypmnt;

    private String dat_sor_prev;

    private String dat_mnq_du;

    private String dat_mnq_au;

    private String dat_ent_ant;

    private String codstat;

    private String dat_vld;

    private String observ1;

    private Integer testeur1;

    private Integer valid_ag;

    private String dat_sais;

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

    public Integer getNum_bt() {
        return num_bt;
    }

    public void setNum_bt(Integer num_bt) {
        this.num_bt = num_bt;
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

    public String getMac_cdmac() {
        return mac_cdmac;
    }

    public void setMac_cdmac(String mac_cdmac) {
        this.mac_cdmac = mac_cdmac;
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

    public Integer getDra_decagen() {
        return dra_decagen;
    }

    public void setDra_decagen(Integer dra_decagen) {
        this.dra_decagen = dra_decagen;
    }

    public String getCdorga() {
        return cdorga;
    }

    public void setCdorga(String cdorga) {
        this.cdorga = cdorga;
    }

    public String getRef_bt() {
        return ref_bt;
    }

    public void setRef_bt(String ref_bt) {
        this.ref_bt = ref_bt;
    }

    public String getDat_bt() {
        return dat_bt;
    }

    public void setDat_bt(String dat_bt) {
        this.dat_bt = dat_bt;
    }

    public String getDat_dt() {
        return dat_dt;
    }

    public void setDat_dt(String dat_dt) {
        this.dat_dt = dat_dt;
    }

    public String getDat_ft() {
        return dat_ft;
    }

    public void setDat_ft(String dat_ft) {
        this.dat_ft = dat_ft;
    }

    public String getVld() {
        return vld;
    }

    public void setVld(String vld) {
        this.vld = vld;
    }

    public String getTyp_tvx() {
        return typ_tvx;
    }

    public void setTyp_tvx(String typ_tvx) {
        this.typ_tvx = typ_tvx;
    }

    public String getHeurdb() {
        return heurdb;
    }

    public void setHeurdb(String heurdb) {
        this.heurdb = heurdb;
    }

    public String getHeurfi() {
        return heurfi;
    }

    public void setHeurfi(String heurfi) {
        this.heurfi = heurfi;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public String getDat_srt() {
        return dat_srt;
    }

    public void setDat_srt(String dat_srt) {
        this.dat_srt = dat_srt;
    }

    public String getHeursr() {
        return heursr;
    }

    public void setHeursr(String heursr) {
        this.heursr = heursr;
    }

    public String getObs_test() {
        return obs_test;
    }

    public void setObs_test(String obs_test) {
        this.obs_test = obs_test;
    }

    public Integer getIndex_dep() {
        return index_dep;
    }

    public void setIndex_dep(Integer index_dep) {
        this.index_dep = index_dep;
    }

    public Integer getIndex_arr() {
        return index_arr;
    }

    public void setIndex_arr(Integer index_arr) {
        this.index_arr = index_arr;
    }

    public String getImmat_ex() {
        return immat_ex;
    }

    public void setImmat_ex(String immat_ex) {
        this.immat_ex = immat_ex;
    }

    public String getNom_chauff() {
        return nom_chauff;
    }

    public void setNom_chauff(String nom_chauff) {
        this.nom_chauff = nom_chauff;
    }

    public String getNum_permis() {
        return num_permis;
    }

    public void setNum_permis(String num_permis) {
        this.num_permis = num_permis;
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

    public Integer getCpt_org() {
        return cpt_org;
    }

    public void setCpt_org(Integer cpt_org) {
        this.cpt_org = cpt_org;
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

    public String getMotif_dep() {
        return motif_dep;
    }

    public void setMotif_dep(String motif_dep) {
        this.motif_dep = motif_dep;
    }

    public String getCdtypmnt() {
        return cdtypmnt;
    }

    public void setCdtypmnt(String cdtypmnt) {
        this.cdtypmnt = cdtypmnt;
    }

    public String getDat_sor_prev() {
        return dat_sor_prev;
    }

    public void setDat_sor_prev(String dat_sor_prev) {
        this.dat_sor_prev = dat_sor_prev;
    }

    public String getDat_mnq_du() {
        return dat_mnq_du;
    }

    public void setDat_mnq_du(String dat_mnq_du) {
        this.dat_mnq_du = dat_mnq_du;
    }

    public String getDat_mnq_au() {
        return dat_mnq_au;
    }

    public void setDat_mnq_au(String dat_mnq_au) {
        this.dat_mnq_au = dat_mnq_au;
    }

    public String getDat_ent_ant() {
        return dat_ent_ant;
    }

    public void setDat_ent_ant(String dat_ent_ant) {
        this.dat_ent_ant = dat_ent_ant;
    }

    public String getCodstat() {
        return codstat;
    }

    public void setCodstat(String codstat) {
        this.codstat = codstat;
    }

    public String getDat_vld() {
        return dat_vld;
    }

    public void setDat_vld(String dat_vld) {
        this.dat_vld = dat_vld;
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

    public Integer getValid_ag() {
        return valid_ag;
    }

    public void setValid_ag(Integer valid_ag) {
        this.valid_ag = valid_ag;
    }

    public String getDat_sais() {
        return dat_sais;
    }

    public void setDat_sais(String dat_sais) {
        this.dat_sais = dat_sais;
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
            ", numbt=" + getNum_bt() +
            ", cdtier='" + getCdtier() + "'" +
            ", cdmac='" + getCdmac() + "'" +
            ", maccdmac='" + getMac_cdmac() + "'" +
            ", cdserv='" + getCdserv() + "'" +
            ", decagen=" + getDecagen() +
            ", dradecagen=" + getDra_decagen() +
            ", cdorga='" + getCdorga() + "'" +
            ", refbt='" + getRef_bt() + "'" +
            ", datbt='" + getDat_bt() + "'" +
            ", datdt='" + getDat_dt() + "'" +
            ", datft='" + getDat_ft() + "'" +
            ", vld='" + getVld() + "'" +
            ", typtvx='" + getTyp_tvx() + "'" +
            ", heurdb='" + getHeurdb() + "'" +
            ", heurfi='" + getHeurfi() + "'" +
            ", observ='" + getObserv() + "'" +
            ", datsrt='" + getDat_srt() + "'" +
            ", heursr='" + getHeursr() + "'" +
            ", obstest='" + getObs_test() + "'" +
            ", indexdep=" + getIndex_dep() +
            ", indexarr=" + getIndex_arr() +
            ", immatex='" + getImmat_ex() + "'" +
            ", nomchauff='" + getNom_chauff() + "'" +
            ", numpermis='" + getNum_permis() + "'" +
            ", etab='" + getEtab() + "'" +
            ", compteur=" + getCompteur() +
            ", cptorg=" + getCpt_org() +
            ", cdtyptr='" + getCdtyptr() + "'" +
            ", decstat='" + getDecstat() + "'" +
            ", testeur=" + getTesteur() +
            ", motifdep='" + getMotif_dep() + "'" +
            ", cdtypmnt='" + getCdtypmnt() + "'" +
            ", datsorprev='" + getDat_sor_prev() + "'" +
            ", datmnqdu='" + getDat_mnq_du() + "'" +
            ", datmnqau='" + getDat_mnq_au() + "'" +
            ", datentant='" + getDat_ent_ant() + "'" +
            ", codstat='" + getCodstat() + "'" +
            ", datvld='" + getDat_vld() + "'" +
            ", observ1='" + getObserv1() + "'" +
            ", testeur1=" + getTesteur1() +
            ", validag=" + getValid_ag() +
            ", datsais='" + getDat_sais() + "'" +
            "}";
    }
}
