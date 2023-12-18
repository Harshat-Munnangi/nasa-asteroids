package org.nasa.neo.service;

import org.nasa.neo.dto.Asteroid;
import org.nasa.neo.dto.AsteroidDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AsteroidService {
    List<Asteroid> getAsteroidsForWeek(String startDate, String endDate);
    AsteroidDetails getAsteroidDetails(String asteroidId);
}