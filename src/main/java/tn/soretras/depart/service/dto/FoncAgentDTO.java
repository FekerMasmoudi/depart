package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.FoncAgent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FoncAgentDTO implements Serializable {

    private String id;

    private String cdfonc;

    private Integer matric;

    private String nom;

    private String prenom;

    private LocalDate dateff;

    private String valide;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCdfonc() {
        return cdfonc;
    }

    public void setCdfonc(String cdfonc) {
        this.cdfonc = cdfonc;
    }

    public Integer getMatric() {
        return matric;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateff() {
        return dateff;
    }

    public void setDateff(LocalDate dateff) {
        this.dateff = dateff;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoncAgentDTO)) {
            return false;
        }

        FoncAgentDTO foncAgentDTO = (FoncAgentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, foncAgentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoncAgentDTO{" +
            "id='" + getId() + "'" +
            ", cdfonc='" + getCdfonc() + "'" +
            ", matric=" + getMatric() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", dateff='" + getDateff() + "'" +
            ", valide='" + getValide() + "'" +
            "}";
    }
}
