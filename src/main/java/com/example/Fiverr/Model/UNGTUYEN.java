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
public class UNGTUYEN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TrangThai",nullable = false)
	private String TrangThai;

    @ManyToOne
    @JoinColumn(name = "MaCV")
    private CV cv;

    @ManyToOne
    @JoinColumn(name = "MaCViec")
    private CONGVIEC congviec;
}
