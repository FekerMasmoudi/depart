package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Groupe} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GroupeDTO implements Serializable {

    private String id;

    @NotNull
    private Integer deccent;

    @NotNull
    private Integer decagenc;

    @NotNull
    private Integer codgrp;

    private String libgrp;

    private String dectyli;

    private String libgrpfr;

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

    public Integer getCodgrp() {
        return codgrp;
    }

    public void setCodgrp(Integer codgrp) {
        this.codgrp = codgrp;
    }

    public String getLibgrp() {
        return libgrp;
    }

    public void setLibgrp(String libgrp) {
        this.libgrp = libgrp;
    }

    public String getDectyli() {
        return dectyli;
    }

    public void setDectyli(String dectyli) {
        this.dectyli = dectyli;
    }

    public String getLibgrpfr() {
        return libgrpfr;
    }

    public void setLibgrpfr(String libgrpfr) {
        this.libgrpfr = libgrpfr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupeDTO)) {
            return false;
        }

        GroupeDTO groupeDTO = (GroupeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, groupeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GroupeDTO{" +
            "id='" + getId() + "'" +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", codgrp=" + getCodgrp() +
            ", libgrp='" + getLibgrp() + "'" +
            ", dectyli='" + getDectyli() + "'" +
            ", libgrpfr='" + getLibgrpfr() + "'" +
            "}";
    }
}
