package com.example.Fiverr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Fiverr.Model.CHUYENNGANH;

import java.util.List;

@Repository
public interface ChuyenNganhRepository extends JpaRepository<CHUYENNGANH, Long>{
}
