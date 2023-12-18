package org.nasa.neo.serviceImpl;

import org.nasa.neo.client.NasaApiClient;
import org.nasa.neo.dto.Asteroid;
import org.nasa.neo.dto.AsteroidApiResponse;
import org.nasa.neo.dto.AsteroidDetails;
import org.nasa.neo.service.AsteroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class AsteroidServiceImpl implements AsteroidService {

    @Value("${nasa.api.key}")
    String apiKey;

    @Autowired
    NasaApiClient nasaApiClient;

    @Override
    public List<Asteroid> getAsteroidsForWeek(String startDate, String endDate) {
        AsteroidApiResponse apiResponse = nasaApiClient.getAsteroidsForWeek(startDate, endDate, apiKey);
        return extractAndSortAsteroids(apiResponse);
    }

    @Override
    public AsteroidDetails getAsteroidDetails(String asteroidId) {
        return nasaApiClient.getAsteroidDetails(asteroidId, apiKey);
    }

    private List<Asteroid> extractAndSortAsteroids(AsteroidApiResponse response) {
        List<Asteroid> asteroids = response.nearEarthObjects().values().stream().flatMap(Arrays::stream).toList();
        return asteroids.stream()
                .filter(asteroid -> !asteroid.closeApproachData().isEmpty())
                .sorted(Comparator.comparingDouble(asteroid -> asteroid.closeApproachData().get(0).missDistance().missDistanceInKilometers()))
                .toList();
    }
}
