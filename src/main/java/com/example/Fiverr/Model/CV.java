package com.example.Fiverr.Model;

import java.sql.Date;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;


// import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // Đánh dấu đây là table trong db
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ho")
    private String Ho;

    @Column(name = "Ten")
    private String Ten;

    @Column(name = "DiaChi")
    private String DiaChi;

    @Column(name = "CCCD",nullable = true, unique = true)
    private String CCCD;

    @Column(name = "SoDT",nullable = false, unique = true)
    private String SoDT;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "Email", unique = true)
    private String Email;

    @Column(name = "NgaySinh")
    private Date NgaySinh;
    
    @Column(name = "KyNangMem")
    private String KyNangMem;
    
    @Column(name = "ChuyenMon")
    private String ChuyenMon;

    @Column(name = "VitriUngTuyen")
    private String VitriUngTuyen;

    @Column(name = "MoTa")
    private String MoTa;
    
    @Column(name = "HocVan")
    private String HocVan;

    @Column(name = "KinhNghiem")
	private String KinhNghiem;

    @Column(name = "ChungChi")
	private String ChungChi;

    @Column(name = "Project")
	private String Project;
    
    @ManyToOne
    // @JsonIgnore
    @JoinColumn(name = "User_id")
    private Users user;

    @Column(name = "diemGPA")
    private Float diemGPA;

    @Column(name = "diemTOEIC")
    private Float diemTOEIC;

    @ManyToOne
    @JoinColumn(name = "id_chuyennganh")
    private CHUYENNGANH chuyennganh;
}

