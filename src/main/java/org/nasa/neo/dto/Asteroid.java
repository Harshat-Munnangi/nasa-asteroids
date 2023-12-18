package org.nasa.neo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Asteroid(
        String id,
        String name,
        @JsonProperty("is_potentially_hazardous_asteroid")
        boolean isPotentiallyHazardous,
        @JsonProperty("close_approach_data")
        List<CloseApproachData> closeApproachData
) {
        public record CloseApproachData(
                @JsonProperty("miss_distance")
                MissDistance missDistance,
                @JsonProperty("close_approach_date")
                @JsonFormat(pattern="yyyy-MM-dd")
                LocalDate approachDate
        ) { }

        public record MissDistance(@JsonProperty("kilometers") String kilometers) {
                public double missDistanceInKilometers() {
                        return Double.parseDouble(kilometers);
                }
        }
}
