package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.ExternalApi} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ExternalApiDTO implements Serializable {

    private String id;

    private String idm;

    private String name;

    private String status;

    private String comments;

    private Integer idschema;

    private LocalDate datecreatedt;

    private LocalDate lastupdatedm;

    private String origin;

    private Integer templateid;

    private Integer idmodule;

    private String uritemplate;

    private Integer priority;

    private Integer schemaidt;

    private LocalDate createdatet;

    private LocalDate lastupdatete;

    private String entity;

    private String parametre;

    private Integer countrowsreq;

    private Integer countrowsres;

    private String frequency;

    private String emergencycode;

    private Boolean satausgetapi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdm() {
        return idm;
    }

    public void setIdm(String idm) {
        this.idm = idm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getIdschema() {
        return idschema;
    }

    public void setIdschema(Integer idschema) {
        this.idschema = idschema;
    }

    public LocalDate getDatecreatedt() {
        return datecreatedt;
    }

    public void setDatecreatedt(LocalDate datecreatedt) {
        this.datecreatedt = datecreatedt;
    }

    public LocalDate getLastupdatedm() {
        return lastupdatedm;
    }

    public void setLastupdatedm(LocalDate lastupdatedm) {
        this.lastupdatedm = lastupdatedm;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getTemplateid() {
        return templateid;
    }

    public void setTemplateid(Integer templateid) {
        this.templateid = templateid;
    }

    public Integer getIdmodule() {
        return idmodule;
    }

    public void setIdmodule(Integer idmodule) {
        this.idmodule = idmodule;
    }

    public String getUritemplate() {
        return uritemplate;
    }

    public void setUritemplate(String uritemplate) {
        this.uritemplate = uritemplate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getSchemaidt() {
        return schemaidt;
    }

    public void setSchemaidt(Integer schemaidt) {
        this.schemaidt = schemaidt;
    }

    public LocalDate getCreatedatet() {
        return createdatet;
    }

    public void setCreatedatet(LocalDate createdatet) {
        this.createdatet = createdatet;
    }

    public LocalDate getLastupdatete() {
        return lastupdatete;
    }

    public void setLastupdatete(LocalDate lastupdatete) {
        this.lastupdatete = lastupdatete;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getParametre() {
        return parametre;
    }

    public void setParametre(String parametre) {
        this.parametre = parametre;
    }

    public Integer getCountrowsreq() {
        return countrowsreq;
    }

    public void setCountrowsreq(Integer countrowsreq) {
        this.countrowsreq = countrowsreq;
    }

    public Integer getCountrowsres() {
        return countrowsres;
    }

    public void setCountrowsres(Integer countrowsres) {
        this.countrowsres = countrowsres;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getEmergencycode() {
        return emergencycode;
    }

    public void setEmergencycode(String emergencycode) {
        this.emergencycode = emergencycode;
    }

    public Boolean getSatausgetapi() {
        return satausgetapi;
    }

    public void setSatausgetapi(Boolean satausgetapi) {
        this.satausgetapi = satausgetapi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExternalApiDTO)) {
            return false;
        }

        ExternalApiDTO externalApiDTO = (ExternalApiDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, externalApiDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExternalApiDTO{" +
            "id='" + getId() + "'" +
            ", idm='" + getIdm() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", comments='" + getComments() + "'" +
            ", idschema=" + getIdschema() +
            ", datecreatedt='" + getDatecreatedt() + "'" +
            ", lastupdatedm='" + getLastupdatedm() + "'" +
            ", origin='" + getOrigin() + "'" +
            ", templateid=" + getTemplateid() +
            ", idmodule=" + getIdmodule() +
            ", uritemplate='" + getUritemplate() + "'" +
            ", priority=" + getPriority() +
            ", schemaidt=" + getSchemaidt() +
            ", createdatet='" + getCreatedatet() + "'" +
            ", lastupdatete='" + getLastupdatete() + "'" +
            ", entity='" + getEntity() + "'" +
            ", parametre='" + getParametre() + "'" +
            ", countrowsreq=" + getCountrowsreq() +
            ", countrowsres=" + getCountrowsres() +
            ", frequency='" + getFrequency() + "'" +
            ", emergencycode='" + getEmergencycode() + "'" +
            ", satausgetapi='" + getSatausgetapi() + "'" +
            "}";
    }
}
