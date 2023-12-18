package org.nasa.neo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nasa.neo.dto.Asteroid;
import org.nasa.neo.dto.AsteroidDetails;
import org.nasa.neo.exception.ErrorDto;
import org.nasa.neo.service.AsteroidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestController
@RequestMapping("/api/asteroids")
@CrossOrigin(origins = "http://localhost:3000")
public class AsteroidController {

    @Autowired
    AsteroidService asteroidService;

    private Logger log = LoggerFactory.getLogger(AsteroidController.class);

    @GetMapping("/week/{startDate}/{endDate}")
    public ResponseEntity<?> getAsteroidsForWeek(@PathVariable String startDate, @PathVariable String endDate) throws JsonProcessingException {
        try {
            List<Asteroid> asteroids = asteroidService.getAsteroidsForWeek(startDate, endDate);
            return new ResponseEntity<>(asteroids, HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            log.warn("Error fetching asteroids data. HTTP Status: " + e.getStatusCode());
            ErrorDto errorDto = new ObjectMapper().readValue(e.getResponseBodyAsString(), ErrorDto.class);
            return new ResponseEntity<>(errorDto, HttpStatus.valueOf(errorDto.code()));
        } catch (HttpServerErrorException e) {
            log.warn("Server error fetching asteroids data. HTTP Status: " + e.getStatusCode());
            return new ResponseEntity<>(e.getStatusCode());
        } catch (MethodArgumentTypeMismatchException | HttpMessageNotReadableException e) {
            log.error("Error parsing asteroids data response.", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error fetching asteroids data.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{asteroidId}")
    public ResponseEntity<?> getAsteroidDetails(@PathVariable String asteroidId) throws JsonProcessingException {
        try {
            AsteroidDetails asteroid = asteroidService.getAsteroidDetails(asteroidId);
            return new ResponseEntity<>(asteroid, HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            log.warn("Error fetching asteroid details. HTTP Status: " + e.getStatusCode());
            ErrorDto errorDto = new ObjectMapper().readValue(e.getResponseBodyAsString(), ErrorDto.class);
            return new ResponseEntity<>(errorDto, HttpStatus.valueOf(errorDto.code()));
        } catch (HttpServerErrorException e) {
            log.warn("Server error fetching asteroid details. HTTP Status: " + e.getStatusCode());
            return new ResponseEntity<>(e.getStatusCode());
        } catch (MethodArgumentTypeMismatchException | HttpMessageNotReadableException e) {
            log.error("Error parsing asteroid details response.", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error fetching asteroid details.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
