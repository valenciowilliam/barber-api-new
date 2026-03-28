package com.barber.barber_api.controller;

import com.barber.barber_api.entity.Barber;
import com.barber.barber_api.entity.Rating;
import com.barber.barber_api.repository.BarberRepository;
import com.barber.barber_api.repository.RatingRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingRepository ratingRepository;
    private final BarberRepository barberRepository;

    public RatingController(RatingRepository ratingRepository, BarberRepository barberRepository) {
        this.ratingRepository = ratingRepository;
        this.barberRepository = barberRepository;
    }

    @PostMapping
    @Transactional
    public Rating addRating(@RequestBody Rating rating) {
        // 1. Save the new rating
        Rating savedRating = ratingRepository.save(rating);

        // 2. Calculate the new average
        Double newAvg = ratingRepository.getAverageRatingForBarber(rating.getBarberId());

        // 3. Update the Barber's ratingAvg field with a rounded whole number
        Barber barber = barberRepository.findById(rating.getBarberId())
                .orElseThrow(() -> new RuntimeException("Barber not found"));

        // Rounding to nearest whole number as requested
        barber.setRatingAvg(newAvg != null ? (double) Math.round(newAvg) : 0.0);
        barberRepository.save(barber);

        return savedRating;
    }

    // ✅ New Endpoint to Clear Ratings for a specific Barber
    @DeleteMapping("/clear/{barberId}")
    @Transactional
    public String clearBarberRatings(@PathVariable Long barberId) {
        // 1. Delete all individual ratings for this barber
        ratingRepository.deleteByBarberId(barberId);

        // 2. Reset the barber's average rating in the Barber table
        Barber barber = barberRepository.findById(barberId)
                .orElseThrow(() -> new RuntimeException("Barber not found"));

        barber.setRatingAvg(0.0);
        barberRepository.save(barber);

        return "Ratings cleared for barber ID: " + barberId;
    }
}