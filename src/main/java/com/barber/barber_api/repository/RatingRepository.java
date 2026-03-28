package com.barber.barber_api.repository;

import com.barber.barber_api.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying; // ✅ Required for DELETE queries
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional; // ✅ Required for data modification

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    // 1. Custom query to calculate the average of all 'rating' values for a specific barberId
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.barberId = :barberId")
    Double getAverageRatingForBarber(@Param("barberId") Long barberId);

    // 2. Custom query to clear all individual ratings for a specific barber
    @Modifying
    @Transactional
    @Query("DELETE FROM Rating r WHERE r.barberId = :barberId")
    void deleteByBarberId(@Param("barberId") Long barberId);
}