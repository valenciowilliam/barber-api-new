package com.barber.barber_api.controller;

import com.barber.barber_api.entity.Appointment;
import com.barber.barber_api.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {

        boolean alreadyBooked =
                appointmentRepository.existsByBarberIdAndAppointmentDateAndStartTime(
                        appointment.getBarberId(),
                        appointment.getAppointmentDate(),
                        appointment.getStartTime()
                );

        if(alreadyBooked){
            throw new RuntimeException("This time slot is already booked");
        }

        appointment.setStatus("BOOKED");

        return appointmentRepository.save(appointment);
    }

    @GetMapping("/user/{id}")
    public List<Appointment> getAppointmentsByUser(@PathVariable Long id) {
        return appointmentRepository.findByUserId(id);
    }

    @DeleteMapping("/clear")
    public String clearAllAppointments() {
        appointmentRepository.deleteAllAppointments(); // ✅ USE THIS
        return "All appointments cleared";
    }

    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return "Appointment deleted";
    }
}