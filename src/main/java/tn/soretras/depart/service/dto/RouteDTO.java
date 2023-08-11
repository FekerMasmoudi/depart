package tn.soretras.depart.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.soretras.depart.domain.Route} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RouteDTO implements Serializable {

    private String id;

    @NotNull
    private String agency_id;

    @NotNull
    private String route_short_name;

    @NotNull
    private String route_long_name;

    @NotNull
    private String route_desc;

    @NotNull
    private String route_type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(String agency_id) {
        this.agency_id = agency_id;
    }

    public String getRoute_short_name() {
        return route_short_name;
    }

    public void setRoute_short_name(String route_short_name) {
        this.route_short_name = route_short_name;
    }

    public String getRoute_long_name() {
        return route_long_name;
    }

    public void setRoute_long_name(String route_long_name) {
        this.route_long_name = route_long_name;
    }

    public String getRoute_desc() {
        return route_desc;
    }

    public void setRoute_desc(String route_desc) {
        this.route_desc = route_desc;
    }

    public String getRoute_type() {
        return route_type;
    }

    public void setRoute_type(String route_type) {
        this.route_type = route_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RouteDTO)) {
            return false;
        }

        RouteDTO routeDTO = (RouteDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, routeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RouteDTO{" +
            "id='" + getId() + "'" +
            ", agency_id='" + getAgency_id() + "'" +
            ", route_short_name='" + getRoute_short_name() + "'" +
            ", route_long_name='" + getRoute_long_name() + "'" +
            ", route_desc='" + getRoute_desc() + "'" +
            ", route_type='" + getRoute_type() + "'" +
            "}";
    }
}
