package com.example.Fiverr.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Fiverr.Model.CHINHANH;
import com.example.Fiverr.Model.CONGTY;
import com.example.Fiverr.Model.DIACHI;
import com.example.Fiverr.Model.UNGTUYEN;

@Repository
public interface ChiNhanhCtyRepository extends JpaRepository<CHINHANH, Long> {  
    @Query(value = "CALL GetTenCtyByDiachi(:diachiId);", nativeQuery = true)
    // @Query(value = "CALL getTenCtyByUserId(:userId);", nativeQuery = true)

    // List<Object[]> GetTenCtyByDiachi();
    List<Object[]> GetTenCtyByDiachi(
            @Param("diachiId") Long diachiId
    );

    List<CHINHANH> findByCongtyId(Long ctyId);


}
