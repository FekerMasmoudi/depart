package tn.soretras.depart.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Route.
 */
@Document(collection = "route")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("agency_id")
    private String agency_id;

    @NotNull
    @Field("route_short_name")
    private String route_short_name;

    @NotNull
    @Field("route_long_name")
    private String route_long_name;

    @NotNull
    @Field("route_desc")
    private String route_desc;

    @NotNull
    @Field("route_type")
    private String route_type;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Route id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgency_id() {
        return this.agency_id;
    }

    public Route agency_id(String agency_id) {
        this.setAgency_id(agency_id);
        return this;
    }

    public void setAgency_id(String agency_id) {
        this.agency_id = agency_id;
    }

    public String getRoute_short_name() {
        return this.route_short_name;
    }

    public Route route_short_name(String route_short_name) {
        this.setRoute_short_name(route_short_name);
        return this;
    }

    public void setRoute_short_name(String route_short_name) {
        this.route_short_name = route_short_name;
    }

    public String getRoute_long_name() {
        return this.route_long_name;
    }

    public Route route_long_name(String route_long_name) {
        this.setRoute_long_name(route_long_name);
        return this;
    }

    public void setRoute_long_name(String route_long_name) {
        this.route_long_name = route_long_name;
    }

    public String getRoute_desc() {
        return this.route_desc;
    }

    public Route route_desc(String route_desc) {
        this.setRoute_desc(route_desc);
        return this;
    }

    public void setRoute_desc(String route_desc) {
        this.route_desc = route_desc;
    }

    public String getRoute_type() {
        return this.route_type;
    }

    public Route route_type(String route_type) {
        this.setRoute_type(route_type);
        return this;
    }

    public void setRoute_type(String route_type) {
        this.route_type = route_type;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Route)) {
            return false;
        }
        return id != null && id.equals(((Route) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Route{" +
            "id=" + getId() +
            ", agency_id='" + getAgency_id() + "'" +
            ", route_short_name='" + getRoute_short_name() + "'" +
            ", route_long_name='" + getRoute_long_name() + "'" +
            ", route_desc='" + getRoute_desc() + "'" +
            ", route_type='" + getRoute_type() + "'" +
            "}";
    }
}
