package org.nasa.neo.client;

import org.nasa.neo.dto.AsteroidApiResponse;
import org.nasa.neo.dto.AsteroidDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class NasaApiClient {

    @Autowired RestTemplate restTemplate;
    @Value("${nasa.api.base-url}")
    String NASA_API_BASE_URL;

    @Cacheable(value = "asteroidList", unless = "#result == null || #result.nearEarthObjects() == null")
    public AsteroidApiResponse getAsteroidsForWeek(String startDate, String endDate, String apiKey) {
        String apiUrl = String.format("%s/feed?start_date=%s&end_date=%s&api_key=%s",
                NASA_API_BASE_URL, startDate, endDate, apiKey);

        AsteroidApiResponse response = restTemplate.getForObject(apiUrl, AsteroidApiResponse.class);

        if (response == null || response.nearEarthObjects() == null) {
            throw new RuntimeException("Invalid response from NASA API");
        }

        return response;
    }

    @Cacheable(value = "asteroidDetails", unless = "#result == null")
    public AsteroidDetails getAsteroidDetails(String asteroidId, String apiKey) {
        String apiUrl = String.format("%s/neo/%s?api_key=%s", NASA_API_BASE_URL, asteroidId, apiKey);

        return restTemplate.getForObject(apiUrl, AsteroidDetails.class);
    }

}
