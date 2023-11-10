package com.example.Fiverr.Model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // Đánh dấu đây là table trong db
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Email", unique = true)
	private String email;

    @Column(name = "username",nullable = false, unique = true)
	private String userName;

    @Column(name = "MatKhau",nullable = false)
    private String MatKhau;

    @Column(name = "ChucVu")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public boolean isAdmin() {
        return RoleEnum.ADMIN.equals(role);
    }

    public Users orElseThrow(Object object) {
        return null;
    }
}

