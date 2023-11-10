package com.example.Fiverr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Fiverr.Model.DIACHI;

@Repository
public interface DiaChiRepository extends JpaRepository<DIACHI, Long> {
    
}
