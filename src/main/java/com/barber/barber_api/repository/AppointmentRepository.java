package com.barber.barber_api.repository;

import com.barber.barber_api.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Get appointments for a user
    List<Appointment> findByUserId(Long userId);

    // Prevent double booking
    boolean existsByBarberIdAndAppointmentDateAndStartTime(
            Long barberId,
            LocalDate appointmentDate,
            LocalTime startTime
    );

    // ✅ Clear all appointments (FIX for your error)
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE appointments RESTART IDENTITY CASCADE", nativeQuery = true)
    void deleteAllAppointments();
}