package com.barber.barber_api.auth;

import com.barber.barber_api.auth.AuthResponse;
import com.barber.barber_api.auth.LoginRequest;
import com.barber.barber_api.auth.RegisterRequest;
import com.barber.barber_api.entity.User;
import com.barber.barber_api.repository.UserRepository;
import com.barber.barber_api.security.JwtService;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // ✅ Updated Constructor
    public AuthController(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 🔐 LOGIN (still basic, will improve in Step 2)
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        // 🔍 Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔐 Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 🎟 Generate token if valid
        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, user.getId());
    }

    // ✅ REGISTER (now saves user in DB with hashed password)
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        // 🔐 Hash password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return "User registered successfully";
    }
}
