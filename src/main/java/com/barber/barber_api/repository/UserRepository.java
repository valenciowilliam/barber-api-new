package com.barber.barber_api.repository;
import java.util.Optional;
import com.barber.barber_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}