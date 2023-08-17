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
    private LocalDate databs;

    @Field("numabs")
    private Integer numabs;

    @Field("nbrabs")
    private Integer nbrabs;

    @Field("validabs")
    private Integer validabs;

    @Field("observaabs")
    private String observaabs;

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

    public Drabsen(
        String id,
        String cdtypab,
        Integer matric,
        LocalDate databs,
        Integer numabs,
        Integer nbrabs,
        Integer validabs,
        String observaabs,
        Integer cd1,
        Integer cd2,
        Integer cd3
    ) {
        this.id = id;
        this.cdtypab = cdtypab;
        this.matric = matric;
        this.databs = databs;
        this.numabs = numabs;
        this.nbrabs = nbrabs;
        this.validabs = validabs;
        this.observaabs = observaabs;
        this.cd1 = cd1;
        this.cd2 = cd2;
        this.cd3 = cd3;
    }

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

    public LocalDate getDatabs() {
        return this.databs;
    }

    public Drabsen databs(LocalDate databs) {
        this.setDatabs(databs);
        return this;
    }

    public void setDatabs(LocalDate databs) {
        this.databs = databs;
    }

    public Integer getNumabs() {
        return this.numabs;
    }

    public Drabsen numabs(Integer numabs) {
        this.setNumabs(numabs);
        return this;
    }

    public void setNumabs(Integer numabs) {
        this.numabs = numabs;
    }

    public Integer getNbrabs() {
        return this.nbrabs;
    }

    public Drabsen nbrabs(Integer nbrabs) {
        this.setNbrabs(nbrabs);
        return this;
    }

    public void setNbrabs(Integer nbrabs) {
        this.nbrabs = nbrabs;
    }

    public Integer getValidabs() {
        return this.validabs;
    }

    public Drabsen validabs(Integer validabs) {
        this.setValidabs(validabs);
        return this;
    }

    public void setValidabs(Integer validabs) {
        this.validabs = validabs;
    }

    public String getObservaabs() {
        return this.observaabs;
    }

    public Drabsen observaabs(String observaabs) {
        this.setObservaabs(observaabs);
        return this;
    }

    public void setObservaabs(String observaabs) {
        this.observaabs = observaabs;
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
            ", databs='" + getDatabs() + "'" +
            ", numabs=" + getNumabs() +
            ", nbrabs=" + getNbrabs() +
            ", validabs=" + getValidabs() +
            ", observaabs='" + getObservaabs() + "'" +
            ", cd1=" + getCd1() +
            ", cd2=" + getCd2() +
            ", cd3=" + getCd3() +
            "}";
    }
}
