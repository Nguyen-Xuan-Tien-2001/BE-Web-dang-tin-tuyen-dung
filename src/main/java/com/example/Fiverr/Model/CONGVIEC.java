package com.example.Fiverr.Model;

import java.sql.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CONGVIEC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TenCViec",nullable = false)
	private String TenCViec;

    @Column(name = "MoTa")
	private String MoTa;

    @Column(name = "Soluong")
	private int Soluong;

    @Column(name = "YeuCau")
	private String YeuCau;

    @Column(name = "Luong")
	private int Luong;

    @Column(name = "NgayDang")
	private Date NgayDang;

    @Column(name = "HanUngTuyen")
	private Date HanUngTuyen;

    @Column(name = "TrangThai")
	private String TrangThai;

    @ManyToOne
    @JoinColumn(name = "MaCN")
    private CHINHANH chinhanh;

    @Column(name = "Image")
    private String image;

    @Column(name = "diemGPA")
    private Float diemGPA;

    @Column(name = "diemTOEIC")
    private Float diemTOEIC;
}
