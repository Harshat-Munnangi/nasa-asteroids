package org.nasa.neo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AsteroidApiResponse(
        @JsonProperty("near_earth_objects")
        Map<String, Asteroid[]> nearEarthObjects
) {
}
