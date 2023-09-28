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
    private Integer num_bt;

    @Field("cdtier")
    private String cdtier;

    @Field("cdmac")
    private String cdmac;

    @Field("maccdmac")
    private String mac_cdmac;

    @Field("cdserv")
    private String cdserv;

    @Field("decagen")
    private Integer decagen;

    @Field("dradecagen")
    private Integer dra_decagen;

    @Field("cdorga")
    private String cdorga;

    @Field("refbt")
    private String ref_bt;

    @Field("datbt")
    private String dat_bt;

    @Field("datdt")
    private String dat_dt;

    @Field("datft")
    private String dat_ft;

    @Field("vld")
    private String vld;

    @Field("typtvx")
    private String typ_tvx;

    @Field("heurdb")
    private String heurdb;

    @Field("heurfi")
    private String heurfi;

    @Field("observ")
    private String observ;

    @Field("datsrt")
    private String dat_srt;

    @Field("heursr")
    private String heursr;

    @Field("obstest")
    private String obs_test;

    @Field("indexdep")
    private Integer index_dep;

    @Field("indexarr")
    private Integer index_arr;

    @Field("immatex")
    private String immat_ex;

    @Field("nomchauff")
    private String nom_chauff;

    @Field("numpermis")
    private String num_permis;

    @Field("etab")
    private String etab;

    @Field("compteur")
    private Integer compteur;

    @Field("cptorg")
    private Integer cpt_org;

    @Field("cdtyptr")
    private String cdtyptr;

    @Field("decstat")
    private String decstat;

    @Field("testeur")
    private Integer testeur;

    @Field("motifdep")
    private String motif_dep;

    @Field("cdtypmnt")
    private String cdtypmnt;

    @Field("datsorprev")
    private String dat_sor_prev;

    @Field("datmnqdu")
    private String dat_mnq_du;

    @Field("datmnqau")
    private String dat_mnq_au;

    @Field("datentant")
    private String dat_ent_ant;

    @Field("codstat")
    private String codstat;

    @Field("datvld")
    private String dat_vld;

    @Field("observ_1")
    private String observ1;

    @Field("testeur_1")
    private Integer testeur1;

    @Field("validag")
    private Integer valid_ag;

    @Field("datsais")
    private String dat_sais;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BonTvx() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BonTvx id(String id) {
        this.setId(id);
        return this;
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

    public Integer getNum_bt() {
        return num_bt;
    }

    public void setNum_bt(Integer num_bt) {
        this.num_bt = num_bt;
    }

    public BonTvx num_bt(Integer num_bt) {
        this.setNum_bt(num_bt);
        return this;
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

    public String getMac_cdmac() {
        return this.mac_cdmac;
    }

    public BonTvx mac_cdmac(String mac_cdmac) {
        this.setMac_cdmac(mac_cdmac);
        return this;
    }

    public void setMac_cdmac(String mac_cdmac) {
        this.mac_cdmac = mac_cdmac;
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

    public Integer getDra_decagen() {
        return this.dra_decagen;
    }

    public BonTvx dra_decagen(Integer dra_decagen) {
        this.setDra_decagen(dra_decagen);
        return this;
    }

    public void setDra_decagen(Integer dra_decagen) {
        this.dra_decagen = dra_decagen;
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

    public String getRef_bt() {
        return this.ref_bt;
    }

    public BonTvx ref_bt(String ref_bt) {
        this.setRef_bt(ref_bt);
        return this;
    }

    public void setRef_bt(String ref_bt) {
        this.ref_bt = ref_bt;
    }

    public String getDat_bt() {
        return this.dat_bt;
    }

    public BonTvx dat_bt(String dat_bt) {
        this.setDat_bt(dat_bt);
        return this;
    }

    public void setDat_bt(String dat_bt) {
        this.dat_bt = dat_bt;
    }

    public String getDat_dt() {
        return this.dat_dt;
    }

    public BonTvx dat_dt(String dat_dt) {
        this.setDat_dt(dat_dt);
        return this;
    }

    public void setDat_dt(String dat_dt) {
        this.dat_dt = dat_dt;
    }

    public String getDat_ft() {
        return this.dat_ft;
    }

    public BonTvx dat_ft(String dat_ft) {
        this.setDat_ft(dat_ft);
        return this;
    }

    public void setDat_ft(String dat_ft) {
        this.dat_ft = dat_ft;
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

    public String getTyp_tvx() {
        return this.typ_tvx;
    }

    public BonTvx typ_tvx(String typ_tvx) {
        this.setTyp_tvx(typ_tvx);
        return this;
    }

    public void setTyp_tvx(String typ_tvx) {
        this.typ_tvx = typ_tvx;
    }

    public String getHeurdb() {
        return this.heurdb;
    }

    public BonTvx heurdb(String heurdb) {
        this.setHeurdb(heurdb);
        return this;
    }

    public void setHeurdb(String heurdb) {
        this.heurdb = heurdb;
    }

    public String getHeurfi() {
        return this.heurfi;
    }

    public BonTvx heurfi(String heurfi) {
        this.setHeurfi(heurfi);
        return this;
    }

    public void setHeurfi(String heurfi) {
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

    public String getDat_srt() {
        return this.dat_srt;
    }

    public BonTvx dat_srt(String dat_srt) {
        this.setDat_srt(dat_srt);
        return this;
    }

    public void setDat_srt(String dat_srt) {
        this.dat_srt = dat_srt;
    }

    public String getHeursr() {
        return this.heursr;
    }

    public BonTvx heursr(String heursr) {
        this.setHeursr(heursr);
        return this;
    }

    public void setHeursr(String heursr) {
        this.heursr = heursr;
    }

    public String getObs_test() {
        return this.obs_test;
    }

    public BonTvx obs_test(String obs_test) {
        this.setObs_test(obs_test);
        return this;
    }

    public void setObs_test(String obs_test) {
        this.obs_test = obs_test;
    }

    public Integer getIndex_dep() {
        return this.index_dep;
    }

    public BonTvx index_dep(Integer index_dep) {
        this.setIndex_dep(index_dep);
        return this;
    }

    public void setIndex_dep(Integer index_dep) {
        this.index_dep = index_dep;
    }

    public Integer getIndex_arr() {
        return this.index_arr;
    }

    public BonTvx index_arr(Integer index_arr) {
        this.setIndex_arr(index_arr);
        return this;
    }

    public void setIndex_arr(Integer index_arr) {
        this.index_arr = index_arr;
    }

    public String getImmat_ex() {
        return this.immat_ex;
    }

    public BonTvx immat_ex(String immat_ex) {
        this.setImmat_ex(immat_ex);
        return this;
    }

    public void setImmat_ex(String immat_ex) {
        this.immat_ex = immat_ex;
    }

    public String getNom_chauff() {
        return this.nom_chauff;
    }

    public BonTvx nom_chauff(String nom_chauff) {
        this.setNom_chauff(nom_chauff);
        return this;
    }

    public void setNom_chauff(String nom_chauff) {
        this.nom_chauff = nom_chauff;
    }

    public String getNum_permis() {
        return this.num_permis;
    }

    public BonTvx num_permis(String num_permis) {
        this.setNum_permis(num_permis);
        return this;
    }

    public void setNum_permis(String num_permis) {
        this.num_permis = num_permis;
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

    public Integer getCpt_org() {
        return this.cpt_org;
    }

    public BonTvx cpt_org(Integer cpt_org) {
        this.setCpt_org(cpt_org);
        return this;
    }

    public void setCpt_org(Integer cpt_org) {
        this.cpt_org = cpt_org;
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

    public String getMotif_dep() {
        return this.motif_dep;
    }

    public BonTvx motif_dep(String motif_dep) {
        this.setMotif_dep(motif_dep);
        return this;
    }

    public void setMotif_dep(String motif_dep) {
        this.motif_dep = motif_dep;
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

    public String getDat_sor_prev() {
        return this.dat_sor_prev;
    }

    public BonTvx dat_sor_prev(String dat_sor_prev) {
        this.setDat_sor_prev(dat_sor_prev);
        return this;
    }

    public void setDat_sor_prev(String dat_sor_prev) {
        this.dat_sor_prev = dat_sor_prev;
    }

    public String getDat_mnq_du() {
        return this.dat_mnq_du;
    }

    public BonTvx dat_mnq_du(String datmnqdu) {
        this.setDat_mnq_du(dat_mnq_du);
        return this;
    }

    public void setDat_mnq_du(String dat_mnq_du) {
        this.dat_mnq_du = dat_mnq_du;
    }

    public String getDat_mnq_au() {
        return this.dat_mnq_au;
    }

    public BonTvx dat_mnq_au(String dat_mnq_au) {
        this.setDat_mnq_au(dat_mnq_au);
        return this;
    }

    public void setDat_mnq_au(String dat_mnq_au) {
        this.dat_mnq_au = dat_mnq_au;
    }

    public String getDat_ent_ant() {
        return this.dat_ent_ant;
    }

    public BonTvx dat_ent_ant(String dat_ent_ant) {
        this.setDat_ent_ant(dat_ent_ant);
        return this;
    }

    public void setDat_ent_ant(String dat_ent_ant) {
        this.dat_ent_ant = dat_ent_ant;
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

    public String getDat_vld() {
        return this.dat_vld;
    }

    public BonTvx dat_vld(String dat_vld) {
        this.setDat_vld(dat_vld);
        return this;
    }

    public void setDat_vld(String dat_vld) {
        this.dat_vld = dat_vld;
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

    public Integer getValid_ag() {
        return this.valid_ag;
    }

    public BonTvx valid_ag(Integer valid_ag) {
        this.setValid_ag(valid_ag);
        return this;
    }

    public void setValid_ag(Integer valid_ag) {
        this.valid_ag = valid_ag;
    }

    public String getDat_sais() {
        return this.dat_sais;
    }

    public BonTvx dat_sais(String dat_sais) {
        this.setDat_sais(dat_sais);
        return this;
    }

    public void setDat_sais(String dat_sais) {
        this.dat_sais = dat_sais;
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
