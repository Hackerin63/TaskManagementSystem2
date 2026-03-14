package com.TaskManage.dto;

import com.TaskManage.dto.AuthResponse;
import com.TaskManage.entity.User;
import com.TaskManage.repository.UserRepository;
import com.TaskManage.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepo,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {

        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void register(RegisterRequest req) {

        User user = new User();

        user.setUsername(req.username);
        user.setEmail(req.email);
        user.setPassword(passwordEncoder.encode(req.password));
        user.setRole(req.role);

        userRepo.save(user);
    }

    public AuthResponse login(LoginRequest req) {

        User user = userRepo.findByEmail(req.email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(req.password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}