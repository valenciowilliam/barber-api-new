package com.barber.barber_api.repository;

import com.barber.barber_api.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {

}