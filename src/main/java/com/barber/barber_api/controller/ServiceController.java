package com.barber.barber_api.controller;

import com.barber.barber_api.entity.Service;
import com.barber.barber_api.repository.ServiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // ✅ Get ALL services (main endpoint)
    @GetMapping
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    // ✅ Keep this ONLY for compatibility with frontend
    // It ignores barberId and returns all services
    @GetMapping("/barber/{id}")
    public List<Service> getServicesByBarber(@PathVariable Long id) {
        return serviceRepository.findAll();
    }
}