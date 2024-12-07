package com.example.health_online_backend.services;

import com.example.health_online_backend.DTO.DoctorResponse;
import com.example.health_online_backend.DTO.AuthResponse;
import com.example.health_online_backend.models.Doctor;
import com.example.health_online_backend.models.User;
import com.example.health_online_backend.repository.DoctorRepository;
import com.example.health_online_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    public AuthResponse addUser(User user) {
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            return new AuthResponse(false, "Email đã tồn tại tồn tại");
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return new AuthResponse(true, "Đăng kí thành công", userRepository.save(user));
    }

    public AuthResponse login(String email, String password) {
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            return new AuthResponse(false, "Username không tồn tại");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return new AuthResponse(false, "Password không đúng");
        }

        // Login thành công
        return new AuthResponse(true, "Login thành công", user);
    }


    public boolean changePassword(String id, String oldPassword, String newPassword) {
        User user = userRepository.findUserById(id);
        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(newPassword);
            String hashedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(hashedPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }


    public boolean updateProfile(String id, String fullName, String phoneNumber) {
        User user = userRepository.findUserById(id);
        if (user != null) {
            user.setFullName(fullName);
            user.setPhoneNumber(phoneNumber);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User getPatientById(String id) {
        return userRepository.findUserById(id);
    }


    public static boolean isValidEmail(String email) {
        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        // Mật khẩu yêu cầu ít nhất 5 ký tự và ít nhất 1 ký tự chữ cái
        String passwordRegex = "^(?=.*[A-Za-z])[a-zA-Z0-9.-]{5,}$";
        return password.matches(passwordRegex);
    }
}
