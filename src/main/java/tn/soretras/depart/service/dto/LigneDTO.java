package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Ligne} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LigneDTO implements Serializable {

    private String id;

    private Integer deccent;

    private Integer decagenc;

    private String denumli;

    private String dectyli;

    private String dectyta;

    private String denomli;

    private String dectyeq;

    private Integer denbrkm;

    private Integer detparc;

    private Integer dedural;

    private Integer dedurrt;

    private Integer detrjva;

    private Integer detrjvr;

    private Integer depiste;

    private String statlig;

    private String lig;

    private Integer lig1;

    private String valide;

    private String denumli2;

    private byte[] kml;

    private String kmlContentType;
    private String description;

    private String mimtype;

    private String filename;

    private String charset;

    private ZonedDateTime lastupdate;

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

    public String getDenumli() {
        return denumli;
    }

    public void setDenumli(String denumli) {
        this.denumli = denumli;
    }

    public String getDectyli() {
        return dectyli;
    }

    public void setDectyli(String dectyli) {
        this.dectyli = dectyli;
    }

    public String getDectyta() {
        return dectyta;
    }

    public void setDectyta(String dectyta) {
        this.dectyta = dectyta;
    }

    public String getDenomli() {
        return denomli;
    }

    public void setDenomli(String denomli) {
        this.denomli = denomli;
    }

    public String getDectyeq() {
        return dectyeq;
    }

    public void setDectyeq(String dectyeq) {
        this.dectyeq = dectyeq;
    }

    public Integer getDenbrkm() {
        return denbrkm;
    }

    public void setDenbrkm(Integer denbrkm) {
        this.denbrkm = denbrkm;
    }

    public Integer getDetparc() {
        return detparc;
    }

    public void setDetparc(Integer detparc) {
        this.detparc = detparc;
    }

    public Integer getDedural() {
        return dedural;
    }

    public void setDedural(Integer dedural) {
        this.dedural = dedural;
    }

    public Integer getDedurrt() {
        return dedurrt;
    }

    public void setDedurrt(Integer dedurrt) {
        this.dedurrt = dedurrt;
    }

    public Integer getDetrjva() {
        return detrjva;
    }

    public void setDetrjva(Integer detrjva) {
        this.detrjva = detrjva;
    }

    public Integer getDetrjvr() {
        return detrjvr;
    }

    public void setDetrjvr(Integer detrjvr) {
        this.detrjvr = detrjvr;
    }

    public Integer getDepiste() {
        return depiste;
    }

    public void setDepiste(Integer depiste) {
        this.depiste = depiste;
    }

    public String getStatlig() {
        return statlig;
    }

    public void setStatlig(String statlig) {
        this.statlig = statlig;
    }

    public String getLig() {
        return lig;
    }

    public void setLig(String lig) {
        this.lig = lig;
    }

    public Integer getLig1() {
        return lig1;
    }

    public void setLig1(Integer lig1) {
        this.lig1 = lig1;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public String getDenumli2() {
        return denumli2;
    }

    public void setDenumli2(String denumli2) {
        this.denumli2 = denumli2;
    }

    public byte[] getKml() {
        return kml;
    }

    public void setKml(byte[] kml) {
        this.kml = kml;
    }

    public String getKmlContentType() {
        return kmlContentType;
    }

    public void setKmlContentType(String kmlContentType) {
        this.kmlContentType = kmlContentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMimtype() {
        return mimtype;
    }

    public void setMimtype(String mimtype) {
        this.mimtype = mimtype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public ZonedDateTime getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(ZonedDateTime lastupdate) {
        this.lastupdate = lastupdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LigneDTO)) {
            return false;
        }

        LigneDTO ligneDTO = (LigneDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ligneDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LigneDTO{" +
            "id='" + getId() + "'" +
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
            ", statlig='" + getStatlig() + "'" +
            ", lig='" + getLig() + "'" +
            ", lig1=" + getLig1() +
            ", valide='" + getValide() + "'" +
            ", denumli2='" + getDenumli2() + "'" +
            ", kml='" + getKml() + "'" +
            ", description='" + getDescription() + "'" +
            ", mimtype='" + getMimtype() + "'" +
            ", filename='" + getFilename() + "'" +
            ", charset='" + getCharset() + "'" +
            ", lastupdate='" + getLastupdate() + "'" +
            "}";
    }
}
