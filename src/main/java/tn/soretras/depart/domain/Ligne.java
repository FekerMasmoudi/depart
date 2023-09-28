package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Ligne.
 */
@Document(collection = "ligne")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Ligne implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("deccent")
    private Integer deccent;

    @Field("decagenc")
    private Integer decagenc;

    @Field("denumli")
    private String denumli;

    @Field("dectyli")
    private String dectyli;

    @Field("dectyta")
    private String dectyta;

    @Field("denomli")
    private String denomli;

    @Field("dectyeq")
    private String dectyeq;

    @Field("denbrkm")
    private Double denbrkm;

    @Field("detparc")
    private String detparc;

    @Field("dedural")
    private Integer dedural;

    @Field("dedurrt")
    private Integer dedurrt;

    @Field("detrjva")
    private Integer detrjva;

    @Field("detrjvr")
    private Integer detrjvr;

    @Field("depiste")
    private Double depiste;

    @Field("statlig")
    private String stat_lig;

    @Field("lig")
    private String lig;

    @Field("lig_1")
    private Integer lig1;

    @Field("valide")
    private String valide;

    @Field("denumli_2")
    private String denumli2;

    @Field("kml")
    private byte[] kml;

    @Field("kml_content_type")
    private String kmlContentType;

    @Field("description")
    private String description;

    @Field("mimtype")
    private String mim_type;

    @Field("filename")
    private String file_name;

    @Field("charset")
    private String char_set;

    @Field("lastupdate")
    private String last_update;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Ligne() {}

    public String getId() {
        return this.id;
    }

    public Ligne id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeccent() {
        return this.deccent;
    }

    public Ligne deccent(Integer deccent) {
        this.setDeccent(deccent);
        return this;
    }

    public void setDeccent(Integer deccent) {
        this.deccent = deccent;
    }

    public Integer getDecagenc() {
        return this.decagenc;
    }

    public Ligne decagenc(Integer decagenc) {
        this.setDecagenc(decagenc);
        return this;
    }

    public void setDecagenc(Integer decagenc) {
        this.decagenc = decagenc;
    }

    public String getDenumli() {
        return this.denumli;
    }

    public Ligne denumli(String denumli) {
        this.setDenumli(denumli);
        return this;
    }

    public void setDenumli(String denumli) {
        this.denumli = denumli;
    }

    public String getDectyli() {
        return this.dectyli;
    }

    public Ligne dectyli(String dectyli) {
        this.setDectyli(dectyli);
        return this;
    }

    public void setDectyli(String dectyli) {
        this.dectyli = dectyli;
    }

    public String getDectyta() {
        return this.dectyta;
    }

    public Ligne dectyta(String dectyta) {
        this.setDectyta(dectyta);
        return this;
    }

    public void setDectyta(String dectyta) {
        this.dectyta = dectyta;
    }

    public String getDenomli() {
        return this.denomli;
    }

    public Ligne denomli(String denomli) {
        this.setDenomli(denomli);
        return this;
    }

    public void setDenomli(String denomli) {
        this.denomli = denomli;
    }

    public String getDectyeq() {
        return this.dectyeq;
    }

    public Ligne dectyeq(String dectyeq) {
        this.setDectyeq(dectyeq);
        return this;
    }

    public void setDectyeq(String dectyeq) {
        this.dectyeq = dectyeq;
    }

    public Double getDenbrkm() {
        return this.denbrkm;
    }

    public Ligne denbrkm(Double denbrkm) {
        this.setDenbrkm(denbrkm);
        return this;
    }

    public void setDenbrkm(Double denbrkm) {
        this.denbrkm = denbrkm;
    }

    public String getDetparc() {
        return this.detparc;
    }

    public Ligne detparc(String detparc) {
        this.setDetparc(detparc);
        return this;
    }

    public void setDetparc(String detparc) {
        this.detparc = detparc;
    }

    public Integer getDedural() {
        return this.dedural;
    }

    public Ligne dedural(Integer dedural) {
        this.setDedural(dedural);
        return this;
    }

    public void setDedural(Integer dedural) {
        this.dedural = dedural;
    }

    public Integer getDedurrt() {
        return this.dedurrt;
    }

    public Ligne dedurrt(Integer dedurrt) {
        this.setDedurrt(dedurrt);
        return this;
    }

    public void setDedurrt(Integer dedurrt) {
        this.dedurrt = dedurrt;
    }

    public Integer getDetrjva() {
        return this.detrjva;
    }

    public Ligne detrjva(Integer detrjva) {
        this.setDetrjva(detrjva);
        return this;
    }

    public void setDetrjva(Integer detrjva) {
        this.detrjva = detrjva;
    }

    public Integer getDetrjvr() {
        return this.detrjvr;
    }

    public Ligne detrjvr(Integer detrjvr) {
        this.setDetrjvr(detrjvr);
        return this;
    }

    public void setDetrjvr(Integer detrjvr) {
        this.detrjvr = detrjvr;
    }

    public Double getDepiste() {
        return this.depiste;
    }

    public Ligne depiste(Double depiste) {
        this.setDepiste(depiste);
        return this;
    }

    public void setDepiste(Double depiste) {
        this.depiste = depiste;
    }

    public String getStat_lig() {
        return this.stat_lig;
    }

    public Ligne stat_lig(String stat_lig) {
        this.setStat_lig(stat_lig);
        return this;
    }

    public void setStat_lig(String stat_lig) {
        this.stat_lig = stat_lig;
    }

    public String getLig() {
        return this.lig;
    }

    public Ligne lig(String lig) {
        this.setLig(lig);
        return this;
    }

    public void setLig(String lig) {
        this.lig = lig;
    }

    public Integer getLig1() {
        return this.lig1;
    }

    public Ligne lig1(Integer lig1) {
        this.setLig1(lig1);
        return this;
    }

    public void setLig1(Integer lig1) {
        this.lig1 = lig1;
    }

    public String getValide() {
        return this.valide;
    }

    public Ligne valide(String valide) {
        this.setValide(valide);
        return this;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public String getDenumli2() {
        return this.denumli2;
    }

    public Ligne denumli2(String denumli2) {
        this.setDenumli2(denumli2);
        return this;
    }

    public void setDenumli2(String denumli2) {
        this.denumli2 = denumli2;
    }

    public byte[] getKml() {
        return this.kml;
    }

    public Ligne kml(byte[] kml) {
        this.setKml(kml);
        return this;
    }

    public void setKml(byte[] kml) {
        this.kml = kml;
    }

    public String getKmlContentType() {
        return this.kmlContentType;
    }

    public Ligne kmlContentType(String kmlContentType) {
        this.kmlContentType = kmlContentType;
        return this;
    }

    public void setKmlContentType(String kmlContentType) {
        this.kmlContentType = kmlContentType;
    }

    public String getDescription() {
        return this.description;
    }

    public Ligne description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMim_type() {
        return this.mim_type;
    }

    public Ligne mim_type(String mim_type) {
        this.setMim_type(mim_type);
        return this;
    }

    public void setMim_type(String mim_type) {
        this.mim_type = mim_type;
    }

    public String getFile_name() {
        return this.file_name;
    }

    public Ligne file_name(String file_name) {
        this.setFile_name(file_name);
        return this;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getChar_set() {
        return this.char_set;
    }

    public Ligne char_set(String char_set) {
        this.setChar_set(char_set);
        return this;
    }

    public void setChar_set(String char_set) {
        this.char_set = char_set;
    }

    public String getLast_update() {
        return this.last_update;
    }

    public Ligne last_update(String last_update) {
        this.setLast_update(last_update);
        return this;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ligne)) {
            return false;
        }
        return id != null && id.equals(((Ligne) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ligne{" +
            "id=" + getId() +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", denumli='" + getDenumli() + "'" +
            ", dectyli='" + getDectyli() + "'" +
            ", dectyta='" + getDectyta() + "'" +
            ", denomli='" + getDenomli() + "'" +
            ", dectyeq='" + getDectyeq() + "'" +
            ", denbrkm=" + getDenbrkm() +
            ", detparc=" + getDetparc() +
            ", dedural=" + getDedural() +
            ", dedurrt=" + getDedurrt() +
            ", detrjva=" + getDetrjva() +
            ", detrjvr=" + getDetrjvr() +
            ", depiste=" + getDepiste() +
            ", statlig='" + getStat_lig() + "'" +
            ", lig='" + getLig() + "'" +
            ", lig1=" + getLig1() +
            ", valide='" + getValide() + "'" +
            ", denumli2='" + getDenumli2() + "'" +
            ", kml='" + getKml() + "'" +
            ", kmlContentType='" + getKmlContentType() + "'" +
            ", description='" + getDescription() + "'" +
            ", mimtype='" + getMim_type() + "'" +
            ", filename='" + getFile_name() + "'" +
            ", charset='" + getChar_set() + "'" +
            ", lastupdate='" + getLast_update() + "'" +
            "}";
    }
}
