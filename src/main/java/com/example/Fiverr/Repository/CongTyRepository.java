package com.example.Fiverr.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.Fiverr.DTO.DetailCongTyRequest;
import com.example.Fiverr.Model.CONGTY;
@Repository
public interface CongTyRepository extends JpaRepository<CONGTY, Long>{
    // @Procedure(name = "GetCongTyChuyenNganh")
    // List<DetailCongTyRequest> getCongTyChuyenNganh();
    @Query(value = "CALL `GetCongTyChuyenNganh()`;", nativeQuery = true)
    List<Object[]> getCongTyChuyenNganh();
    // @Query(value = "SELECT congty.tencty, chuyennganh.ten_chuyen_nganh, diachi.tendc " +
    //                "FROM congty " +
    //                "INNER JOIN chuyennganh ON congty.id = chuyennganh.cty_id " +
    //                "INNER JOIN chinhanh ON chinhanh.cty_id = congty.id " +
    //                "INNER JOIN diachi ON chinhanh.address_id = diachi.id", nativeQuery = true)
    // List<Object[]> getCongTyChuyenNganh();
}
