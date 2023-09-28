package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Drabsen.
 */
@Document(collection = "drabsen")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Drabsen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("cdtypab")
    private String cdtypab;

    @Field("matric")
    private Integer matric;

    @Field("databs")
    private String dat_abs;

    @Field("numabs")
    private Integer num_abs;

    @Field("nbrabs")
    private Integer nbr_abs;

    @Field("validabs")
    private Integer valid_abs;

    @Field("observaabs")
    private String observa_abs;

    @Field("cd_1")
    private Integer cd1;

    @Field("cd_2")
    private Integer cd2;

    @Field("cd_3")
    private Integer cd3;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Drabsen() {}

    public Drabsen id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCdtypab() {
        return this.cdtypab;
    }

    public Drabsen cdtypab(String cdtypab) {
        this.setCdtypab(cdtypab);
        return this;
    }

    public void setCdtypab(String cdtypab) {
        this.cdtypab = cdtypab;
    }

    public Integer getMatric() {
        return this.matric;
    }

    public Drabsen matric(Integer matric) {
        this.setMatric(matric);
        return this;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public String getDat_abs() {
        return this.dat_abs;
    }

    public Drabsen dat_abs(String dat_abs) {
        this.setDat_abs(dat_abs);
        return this;
    }

    public void setDat_abs(String dat_abs) {
        this.dat_abs = dat_abs;
    }

    public Integer getNum_abs() {
        return this.num_abs;
    }

    public Drabsen num_abs(Integer num_abs) {
        this.setNum_abs(num_abs);
        return this;
    }

    public void setNum_abs(Integer num_abs) {
        this.num_abs = num_abs;
    }

    public Integer getNbr_abs() {
        return this.nbr_abs;
    }

    public Drabsen nbr_abs(Integer nbr_abs) {
        this.setNbr_abs(nbr_abs);
        return this;
    }

    public void setNbr_abs(Integer nbr_abs) {
        this.nbr_abs = nbr_abs;
    }

    public Integer getValid_abs() {
        return this.valid_abs;
    }

    public Drabsen valid_abs(Integer valid_abs) {
        this.setValid_abs(valid_abs);
        return this;
    }

    public void setValid_abs(Integer valid_abs) {
        this.valid_abs = valid_abs;
    }

    public String getObserva_abs() {
        return this.observa_abs;
    }

    public Drabsen observa_abs(String observa_abs) {
        this.setObserva_abs(observa_abs);
        return this;
    }

    public void setObserva_abs(String observa_abs) {
        this.observa_abs = observa_abs;
    }

    public Integer getCd1() {
        return this.cd1;
    }

    public Drabsen cd1(Integer cd1) {
        this.setCd1(cd1);
        return this;
    }

    public void setCd1(Integer cd1) {
        this.cd1 = cd1;
    }

    public Integer getCd2() {
        return this.cd2;
    }

    public Drabsen cd2(Integer cd2) {
        this.setCd2(cd2);
        return this;
    }

    public void setCd2(Integer cd2) {
        this.cd2 = cd2;
    }

    public Integer getCd3() {
        return this.cd3;
    }

    public Drabsen cd3(Integer cd3) {
        this.setCd3(cd3);
        return this;
    }

    public void setCd3(Integer cd3) {
        this.cd3 = cd3;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Drabsen)) {
            return false;
        }
        return id != null && id.equals(((Drabsen) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Drabsen{" +
            "id=" + getId() +
            ", cdtypab='" + getCdtypab() + "'" +
            ", matric=" + getMatric() +
            ", databs='" + getDat_abs() + "'" +
            ", numabs=" + getNum_abs() +
            ", nbrabs=" + getNbr_abs() +
            ", validabs=" + getValid_abs() +
            ", observaabs='" + getObserva_abs() + "'" +
            ", cd1=" + getCd1() +
            ", cd2=" + getCd2() +
            ", cd3=" + getCd3() +
            "}";
    }
}
