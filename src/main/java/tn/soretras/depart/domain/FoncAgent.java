package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A FoncAgent.
 */
@Document(collection = "fonc_agent")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FoncAgent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("cdfonc")
    private String cdfonc;

    @Field("matric")
    private Integer matric;

    @Field("nom")
    private String nom;

    @Field("prenom")
    private String prenom;

    @Field("dateff")
    private LocalDate dateff;

    @Field("valide")
    private String valide;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public FoncAgent id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCdfonc() {
        return this.cdfonc;
    }

    public FoncAgent cdfonc(String cdfonc) {
        this.setCdfonc(cdfonc);
        return this;
    }

    public void setCdfonc(String cdfonc) {
        this.cdfonc = cdfonc;
    }

    public Integer getMatric() {
        return this.matric;
    }

    public FoncAgent matric(Integer matric) {
        this.setMatric(matric);
        return this;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public String getNom() {
        return this.nom;
    }

    public FoncAgent nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public FoncAgent prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateff() {
        return this.dateff;
    }

    public FoncAgent dateff(LocalDate dateff) {
        this.setDateff(dateff);
        return this;
    }

    public void setDateff(LocalDate dateff) {
        this.dateff = dateff;
    }

    public String getValide() {
        return this.valide;
    }

    public FoncAgent valide(String valide) {
        this.setValide(valide);
        return this;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoncAgent)) {
            return false;
        }
        return id != null && id.equals(((FoncAgent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoncAgent{" +
            "id=" + getId() +
            ", cdfonc='" + getCdfonc() + "'" +
            ", matric=" + getMatric() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", dateff='" + getDateff() + "'" +
            ", valide='" + getValide() + "'" +
            "}";
    }
}
