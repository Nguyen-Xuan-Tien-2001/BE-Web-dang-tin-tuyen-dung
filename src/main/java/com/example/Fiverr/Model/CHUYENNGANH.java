package com.example.Fiverr.Model;

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
public class CHUYENNGANH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "TenChuyenNganh",nullable = false)
	private String TenChuyenNganh;

    @ManyToOne
    @JoinColumn(name = "Cty_id")
    private CONGTY congty;
}
