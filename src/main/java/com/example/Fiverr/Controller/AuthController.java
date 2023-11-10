package com.example.Fiverr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fiverr.DTO.ChangePasswordRequest;
import com.example.Fiverr.DTO.LoginRequest;
import com.example.Fiverr.Model.RoleEnum;
import com.example.Fiverr.Model.Users;
import com.example.Fiverr.Response.APIResponse;
import com.example.Fiverr.Service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    // private final UserDetailsService userDetailsService;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        // this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public APIResponse register(@RequestBody LoginRequest registrationRequest) {
        // Lấy thông tin đăng ký từ đối tượng registrationRequest
        String username = registrationRequest.getUsername();
        String password = registrationRequest.getPassword();
        String role = registrationRequest.getRole();

        // Tạo đối tượng User với thông tin đăng ký
        Users user = new Users();
        user.setUserName(username);
        user.setMatKhau(passwordEncoder.encode(password));
        if ("USER".equals(role)) {
            user.setRole(RoleEnum.USER);
        } else if ("HR".equals(role)) {
            user.setRole(RoleEnum.HR);
        }

        try {
            // Tạo tài khoản người dùng
            userService.createUser(user);
            APIResponse response = new APIResponse(true, user, "REGISTER SUCCESS!");
            return response;
        } catch (IllegalArgumentException e) {
            APIResponse response = new APIResponse(false, null, "REGISTER FAILED! USERNAME OR EMAIL ALREADY USED!");
            return response;
        }
    }

    @PostMapping("/login")
    public APIResponse login(@RequestBody LoginRequest loginRequest) {
        // Tạo đối tượng User với thông tin đăng ký
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        // user.setPassword(password);

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            UserDetails userDetails = userService.loadUserByUsername(username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                for (GrantedAuthority authority : authentication.getAuthorities()) {
                    String roleName = authority.getAuthority();
                    // Xử lý tên quyền (vai trò) ở đây
                    System.out.println("Tên quyền: " + roleName);
                }
            }
            Users user = userService.getUserByName(username);
            APIResponse response = new APIResponse(true, user, "LOGIN SUCCESS!");
            return response;
        } catch (Exception e) {
            // Xử lý khi xác thực thất bại
            APIResponse response = new APIResponse(false, null, "LOGIN FAILED. CHECK YOUR USERNAME OR PASSWORD AGAIN!");
            return response;
        }
    }

    @PostMapping("/change-password/{id}")
    public APIResponse changePassword(@PathVariable("id") Long id,
    @RequestBody ChangePasswordRequest request) {
    userService.changePassword(id, request.getOldPassword(),
    request.getNewPassword());
    APIResponse response = new APIResponse(true, null, "Doi mat khau thanh cong");
    return response;
    }

    @PostMapping("/forgot-password")
    public APIResponse forgotPassword(@RequestParam("email") String email) {
        try {
            userService.sendPassToEmail(email);
            APIResponse response = new APIResponse(true, null, "Mật khẩu đã được gửi đến email. Vui lòng kiểm tra lại!");
            return response;
        } catch (IllegalArgumentException e) {
            APIResponse response = new APIResponse(false, e, "Vui lòng kiểm tra lại Email hoặc tài khoản của bạn!");
            return response;
        } catch (Exception e) {
            APIResponse response = new APIResponse(false, e, "Vui lòng kiểm tra lại Email hoặc tài khoản của bạn!");
            return response;
        }
    }

}