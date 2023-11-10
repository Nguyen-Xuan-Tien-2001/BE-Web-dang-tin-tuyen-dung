package com.example.Fiverr.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Fiverr.DTO.ForgotPasswordRequest;
import com.example.Fiverr.Model.RoleEnum;
import com.example.Fiverr.Model.Users;
import com.example.Fiverr.Repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    // @Autowired
    // private PasswordEncoder passwordEncoder;
    public Users findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public Users createUser(Users user) {
            if (findByUsername(user.getUserName()) != null) {
                throw new IllegalArgumentException("Người dùng đã tồn tại");
            }
        Users savedUser = userRepository.save(user);
            return savedUser;
        }
        
    public List<Users> getAllUsers() {
        return (List<Users>) userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users getUserByName(String Username) {
        return userRepository.findByUserName(Username);
    }

    public Users updateUser(Long id, Users user) {
        return userRepository.save(user);
    }

    // @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // Vai trò user mặc định

        if (user.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // Vai trò admin
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getMatKhau(),
                authorities);
    }

    public void changePassword(Long userId, String oldPassword, String newPassword) {
        Users user = getUserById(userId);
        if (passwordEncoder.matches(oldPassword, user.getMatKhau())) {
            // Mật khẩu cũ khớp, tiến hành thay đổi mật khẩu
            // String newEncodedPassword = passwordEncoder.encode(newPassword);
            // user.setPassword(passwordEncoder.encode(password));
            user.setMatKhau(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Incorrect old password");
        }
    }

    public void sendPassToEmail(String email) {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User with email " + email + " not found.");
        }
        String pass = generatePass();
        // Gửi mã OTP qua email
        user.setMatKhau(passwordEncoder.encode(pass));
        userRepository.save(user);
        sendOTPEmail(email, pass);
    }

    private String generatePass() {
        int otpLength = 6;
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder(otpLength);

        for (int i = 0; i < otpLength; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }

    private void sendOTPEmail(String userEmail, String pass) {
        // Gửi mã OTP qua email sử dụng JavaMailSender
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("OTP for Password Reset");
        message.setText("Your OTP: " + pass);
        javaMailSender.send(message);
    }
}