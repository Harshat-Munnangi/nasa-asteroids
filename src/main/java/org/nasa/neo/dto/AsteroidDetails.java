package org.nasa.neo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AsteroidDetails(
        @JsonProperty("id") String id,
        @JsonProperty("neo_reference_id") String neoReferenceId,
        @JsonProperty("name") String name,
        @JsonProperty("designation") String designation,
        @JsonProperty("nasa_jpl_url") String nasaJplUrl,
        @JsonProperty("absolute_magnitude_h") double magnitude,
        @JsonProperty("is_potentially_hazardous_asteroid") boolean isHazardous
) {
}
