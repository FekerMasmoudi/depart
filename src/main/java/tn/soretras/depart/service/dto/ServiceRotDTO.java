package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.ServiceRot} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ServiceRotDTO implements Serializable {

    private String id;

    @NotNull
    private Integer deccent;

    @NotNull
    private Integer decagenc;

    @NotNull
    private Integer decserv;

    @NotNull
    private Integer codgrp;

    private String delserv;

    private Integer ord_serv;

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

    public Integer getDecserv() {
        return decserv;
    }

    public void setDecserv(Integer decserv) {
        this.decserv = decserv;
    }

    public Integer getCodgrp() {
        return codgrp;
    }

    public void setCodgrp(Integer codgrp) {
        this.codgrp = codgrp;
    }

    public String getDelserv() {
        return delserv;
    }

    public void setDelserv(String delserv) {
        this.delserv = delserv;
    }

    public Integer getOrd_serv() {
        return ord_serv;
    }

    public void setOrd_serv(Integer ord_serv) {
        this.ord_serv = ord_serv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceRotDTO)) {
            return false;
        }

        ServiceRotDTO serviceRotDTO = (ServiceRotDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, serviceRotDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceRotDTO{" +
            "id='" + getId() + "'" +
            ", deccent=" + getDeccent() +
            ", decagenc=" + getDecagenc() +
            ", decserv=" + getDecserv() +
            ", codgrp=" + getCodgrp() +
            ", delserv='" + getDelserv() + "'" +
            ", ordserv=" + getOrd_serv() +
            "}";
    }
}
