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
@Table(name = "chinhanh")
public class CHINHANH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @ManyToOne
    @JoinColumn(name = "Cty_id")
    private CONGTY congty;

    @ManyToOne
    @JoinColumn(name = "Address_id")
    private DIACHI diachi;
}

