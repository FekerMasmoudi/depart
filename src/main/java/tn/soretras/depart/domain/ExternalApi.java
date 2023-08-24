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
    private Integer idm;

    @Field("name")
    private String name;

    @Field("status")
    private String status;

    @Field("comments")
    private String comments;

    @Field("idschema")
    private Integer idschema;

    @Field("datecreatedt")
    private LocalDate datecreatedt;

    @Field("lastupdatedm")
    private LocalDate lastupdatedm;

    @Field("origin")
    private String origin;

    @Field("templateid")
    private Integer templateid;

    @Field("idmodule")
    private Integer idmodule;

    @Field("uritemplate")
    private String uritemplate;

    @Field("priority")
    private Integer priority;

    @Field("schemaidt")
    private Integer schemaidt;

    @Field("createdatet")
    private LocalDate createdatet;

    @Field("lastupdatete")
    private LocalDate lastupdatete;

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

    public Integer getIdm() {
        return this.idm;
    }

    public ExternalApi idm(Integer idm) {
        this.setIdm(idm);
        return this;
    }

    public void setIdm(Integer idm) {
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

    public Integer getIdschema() {
        return this.idschema;
    }

    public ExternalApi idschema(Integer idschema) {
        this.setIdschema(idschema);
        return this;
    }

    public void setIdschema(Integer idschema) {
        this.idschema = idschema;
    }

    public LocalDate getDatecreatedt() {
        return this.datecreatedt;
    }

    public ExternalApi datecreatedt(LocalDate datecreatedt) {
        this.setDatecreatedt(datecreatedt);
        return this;
    }

    public void setDatecreatedt(LocalDate datecreatedt) {
        this.datecreatedt = datecreatedt;
    }

    public LocalDate getLastupdatedm() {
        return this.lastupdatedm;
    }

    public ExternalApi lastupdatedm(LocalDate lastupdatedm) {
        this.setLastupdatedm(lastupdatedm);
        return this;
    }

    public void setLastupdatedm(LocalDate lastupdatedm) {
        this.lastupdatedm = lastupdatedm;
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

    public Integer getTemplateid() {
        return this.templateid;
    }

    public ExternalApi templateid(Integer templateid) {
        this.setTemplateid(templateid);
        return this;
    }

    public void setTemplateid(Integer templateid) {
        this.templateid = templateid;
    }

    public Integer getIdmodule() {
        return this.idmodule;
    }

    public ExternalApi idmodule(Integer idmodule) {
        this.setIdmodule(idmodule);
        return this;
    }

    public void setIdmodule(Integer idmodule) {
        this.idmodule = idmodule;
    }

    public String getUritemplate() {
        return this.uritemplate;
    }

    public ExternalApi uritemplate(String uritemplate) {
        this.setUritemplate(uritemplate);
        return this;
    }

    public void setUritemplate(String uritemplate) {
        this.uritemplate = uritemplate;
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

    public Integer getSchemaidt() {
        return this.schemaidt;
    }

    public ExternalApi schemaidt(Integer schemaidt) {
        this.setSchemaidt(schemaidt);
        return this;
    }

    public void setSchemaidt(Integer schemaidt) {
        this.schemaidt = schemaidt;
    }

    public LocalDate getCreatedatet() {
        return this.createdatet;
    }

    public ExternalApi createdatet(LocalDate createdatet) {
        this.setCreatedatet(createdatet);
        return this;
    }

    public void setCreatedatet(LocalDate createdatet) {
        this.createdatet = createdatet;
    }

    public LocalDate getLastupdatete() {
        return this.lastupdatete;
    }

    public ExternalApi lastupdatete(LocalDate lastupdatete) {
        this.setLastupdatete(lastupdatete);
        return this;
    }

    public void setLastupdatete(LocalDate lastupdatete) {
        this.lastupdatete = lastupdatete;
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
