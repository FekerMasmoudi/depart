package tn.soretras.depart.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A ExternalApi.
 */
@Document(collection = "external_api")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ExternalApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("idm")
    private String idm;

    @Field("name")
    private String name;

    @Field("status")
    private String status;

    @Field("comments")
    private String comments;

    @Field("idschema")
    private Integer id_schema;

    @Field("datecreatedt")
    private String date_created_t;

    @Field("lastupdatedm")
    private String last_updated_m;

    @Field("origin")
    private String origin;

    @Field("templateid")
    private Integer template_id;

    @Field("idmodule")
    private Integer id_module;

    @Field("uritemplate")
    private String uri_template;

    @Field("priority")
    private Integer priority;

    @Field("schemaidt")
    private Integer schema_id_t;

    @Field("createdatet")
    private String create_date_t;

    @Field("lastupdatete")
    private String lat_update_te;

    @Field("entity")
    private String entity;

    @Field("parametre")
    private String parametre;

    @Field("countrowsreq")
    private Integer countrowsreq;

    @Field("countrowsres")
    private Integer countrowsres;

    @Field("frequency")
    private String frequency;

    @Field("emergencycode")
    private String emergencycode;

    @Field("satausgetapi")
    private Boolean satausgetapi;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ExternalApi id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdm() {
        return this.idm;
    }

    public ExternalApi idm(String idm) {
        this.setIdm(idm);
        return this;
    }

    public void setIdm(String idm) {
        this.idm = idm;
    }

    public String getName() {
        return this.name;
    }

    public ExternalApi name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public ExternalApi status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return this.comments;
    }

    public ExternalApi comments(String comments) {
        this.setComments(comments);
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getId_schema() {
        return this.id_schema;
    }

    public ExternalApi id_schema(Integer id_schema) {
        this.setId_schema(id_schema);
        return this;
    }

    public void setId_schema(Integer id_schema) {
        this.id_schema = id_schema;
    }

    public String getDate_created_t() {
        return this.date_created_t;
    }

    public ExternalApi date_created_t(String date_created_t) {
        this.setDate_created_t(date_created_t);
        return this;
    }

    public void setDate_created_t(String date_created_t) {
        this.date_created_t = date_created_t;
    }

    public String getLast_updated_m() {
        return this.last_updated_m;
    }

    public ExternalApi last_updated_m(String last_updated_m) {
        this.setLast_updated_m(last_updated_m);
        return this;
    }

    public void setLast_updated_m(String last_updated_m) {
        this.last_updated_m = last_updated_m;
    }

    public String getOrigin() {
        return this.origin;
    }

    public ExternalApi origin(String origin) {
        this.setOrigin(origin);
        return this;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getTemplate_id() {
        return this.template_id;
    }

    public ExternalApi template_id(Integer template_id) {
        this.setTemplate_id(template_id);
        return this;
    }

    public void setTemplate_id(Integer template_id) {
        this.template_id = template_id;
    }

    public Integer getId_module() {
        return this.id_module;
    }

    public ExternalApi id_module(Integer id_module) {
        this.setId_module(id_module);
        return this;
    }

    public void setId_module(Integer id_module) {
        this.id_module = id_module;
    }

    public String getUri_template() {
        return this.uri_template;
    }

    public ExternalApi uri_template(String uri_template) {
        this.setUri_template(uri_template);
        return this;
    }

    public void setUri_template(String uri_template) {
        this.uri_template = uri_template;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public ExternalApi priority(Integer priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getSchema_id_t() {
        return this.schema_id_t;
    }

    public ExternalApi schema_id_t(Integer schema_id_t) {
        this.setSchema_id_t(schema_id_t);
        return this;
    }

    public void setSchema_id_t(Integer schema_id_t) {
        this.schema_id_t = schema_id_t;
    }

    public String getCreate_date_t() {
        return this.create_date_t;
    }

    public ExternalApi create_date_t(String create_date_t) {
        this.setCreate_date_t(create_date_t);
        return this;
    }

    public void setCreate_date_t(String create_date_t) {
        this.create_date_t = create_date_t;
    }

    public String getLat_update_te() {
        return this.lat_update_te;
    }

    public ExternalApi lat_update_te(String lat_update_te) {
        this.setLat_update_te(lat_update_te);
        return this;
    }

    public void setLat_update_te(String lat_update_te) {
        this.lat_update_te = lat_update_te;
    }

    public String getEntity() {
        return this.entity;
    }

    public ExternalApi entity(String entity) {
        this.setEntity(entity);
        return this;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getParametre() {
        return this.parametre;
    }

    public ExternalApi parametre(String parametre) {
        this.setParametre(parametre);
        return this;
    }

    public void setParametre(String parametre) {
        this.parametre = parametre;
    }

    public Integer getCountrowsreq() {
        return this.countrowsreq;
    }

    public ExternalApi countrowsreq(Integer countrowsreq) {
        this.setCountrowsreq(countrowsreq);
        return this;
    }

    public void setCountrowsreq(Integer countrowsreq) {
        this.countrowsreq = countrowsreq;
    }

    public Integer getCountrowsres() {
        return this.countrowsres;
    }

    public ExternalApi countrowsres(Integer countrowsres) {
        this.setCountrowsres(countrowsres);
        return this;
    }

    public void setCountrowsres(Integer countrowsres) {
        this.countrowsres = countrowsres;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public ExternalApi frequency(String frequency) {
        this.setFrequency(frequency);
        return this;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getEmergencycode() {
        return this.emergencycode;
    }

    public ExternalApi emergencycode(String emergencycode) {
        this.setEmergencycode(emergencycode);
        return this;
    }

    public void setEmergencycode(String emergencycode) {
        this.emergencycode = emergencycode;
    }

    public Boolean getSatausgetapi() {
        return this.satausgetapi;
    }

    public ExternalApi satausgetapi(Boolean satausgetapi) {
        this.setSatausgetapi(satausgetapi);
        return this;
    }

    public void setSatausgetapi(Boolean satausgetapi) {
        this.satausgetapi = satausgetapi;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExternalApi)) {
            return false;
        }
        return id != null && id.equals(((ExternalApi) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExternalApi{" +
            "id=" + getId() +
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
            ", satausgetapi='" + getSatausgetapi() + "'" +
            "}";
    }
}
