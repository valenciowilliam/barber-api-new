package com.barber.barber_api.controller;

import com.barber.barber_api.entity.Barber;
import com.barber.barber_api.repository.BarberRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbers")
public class BarberController {

    private final BarberRepository barberRepository;

    public BarberController(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    @GetMapping
    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }
}