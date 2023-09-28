package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link tn.soretras.depart.domain.ExternalApi} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ExternalApiDTO implements Serializable {

    private String id;

    private String keycomp;

    private String idm;

    private String name;

    private String status;

    private String comments;

    private Integer id_schema;

    private String date_created_t;

    private String last_updated_m;

    private String origin;

    private Integer template_id;

    private Integer id_module;

    private String uri_template;

    private Integer priority;

    private Integer schema_id_t;

    private String create_date_t;

    private String lat_update_te;

    private String entity;

    private String parametre;

    private Integer countrowsreq;

    private Integer countrowsres;

    private String frequency;

    private String emergencycode;

    private Boolean statusgetapi;

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

    public Integer getId_schema() {
        return id_schema;
    }

    public void setId_schema(Integer id_schema) {
        this.id_schema = id_schema;
    }

    public String getDate_created_t() {
        return date_created_t;
    }

    public void setDate_created_t(String date_created_t) {
        this.date_created_t = date_created_t;
    }

    public String getLast_updated_m() {
        return last_updated_m;
    }

    public void setLast_updated_m(String last_updated_m) {
        this.last_updated_m = last_updated_m;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(Integer template_id) {
        this.template_id = template_id;
    }

    public Integer getId_module() {
        return id_module;
    }

    public void setId_module(Integer id_module) {
        this.id_module = id_module;
    }

    public String getUri_template() {
        return uri_template;
    }

    public void setUri_template(String uri_template) {
        this.uri_template = uri_template;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getSchema_id_t() {
        return schema_id_t;
    }

    public void setSchema_id_t(Integer schema_id_t) {
        this.schema_id_t = schema_id_t;
    }

    public String getCreate_date_t() {
        return create_date_t;
    }

    public void setCreate_date_t(String create_date_t) {
        this.create_date_t = create_date_t;
    }

    public String getLat_update_te() {
        return lat_update_te;
    }

    public void setLat_update_te(String lat_update_te) {
        this.lat_update_te = lat_update_te;
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

    public Boolean getStatusgetapi() {
        return statusgetapi;
    }

    public void setStatusgetapi(Boolean statusgetapi) {
        this.statusgetapi = statusgetapi;
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
            ", idschema=" + getId_schema() +
            ", datecreatedt='" + getDate_created_t() + "'" +
            ", lastupdatedm='" + getLast_updated_m() + "'" +
            ", origin='" + getOrigin() + "'" +
            ", templateid=" + getTemplate_id() +
            ", idmodule=" + getId_module() +
            ", uritemplate='" + getUri_template() + "'" +
            ", priority=" + getPriority() +
            ", schemaidt=" + getSchema_id_t() +
            ", createdatet='" + getCreate_date_t() + "'" +
            ", lastupdatete='" + getLat_update_te() + "'" +
            ", entity='" + getEntity() + "'" +
            ", parametre='" + getParametre() + "'" +
            ", countrowsreq=" + getCountrowsreq() +
            ", countrowsres=" + getCountrowsres() +
            ", frequency='" + getFrequency() + "'" +
            ", emergencycode='" + getEmergencycode() + "'" +
            ", satausgetapi='" + getStatusgetapi() + "'" +
            "}";
    }

    public String getKeycomp() {
        return keycomp;
    }

    public void setKeycomp(String keycomp) {
        this.keycomp = keycomp;
    }
}
