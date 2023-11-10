package com.example.Fiverr.Repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Fiverr.DTO.CongViecResult;
import com.example.Fiverr.Model.CONGVIEC;
@Repository
public interface CongViecRepository extends JpaRepository<CONGVIEC, Long>{
    @Query(value = "CALL getTenCtyByUserId(:userId);", nativeQuery = true)
    // List<Object[]> getCongTyChuyenNganh();
    List<Object[]> getTenCtyByUserId(
            @Param("userId") Long userId
    );
    @Query(value = "CALL GetCongViecCTY(:chinhanhId);", nativeQuery = true)
    // List<Object[]> getCongTyChuyenNganh();
    List<Object[]> GetCongViecCTY(
            @Param("chinhanhId") Long chinhanhId
    );
    @Query(value = "CALL GetCongViecByLuong(:minLuong, :maxLuong)", nativeQuery = true)
    List<CONGVIEC> getCongViecByLuongRange(BigDecimal minLuong, BigDecimal maxLuong);

    // lấy danh sách công việc theo tên chuyên ngành
    @Query(value = "CALL GetCongViecByChuyenNganh(:tenchuyennganh)", nativeQuery = true)
    List<CONGVIEC> getCongViecByChuyenNganh(@Param("tenchuyennganh") String tenchuyennganh);

    // lấy danh sách công việc theo tên công việc, địa chỉ, tên Chuyên Ngành, mức lương
    @Query(value = "CALL GetCongViecByTenAndDiaChiAndChuyenNganhAndLuong(:tenCongViec, :idDiaChi, :tenChuyenNganh, :minLuong, :maxLuong)", nativeQuery = true)
    List<CONGVIEC> getCongViecByTenAndDiaChiAndChuyenNganhAndLuong(@Param("tenCongViec") String tenCongViec, @Param("idDiaChi") Long idDiaChi, @Param("tenChuyenNganh") String tenChuyenNganh, @Param("minLuong") BigDecimal minLuong, @Param("maxLuong") BigDecimal maxLuong);

    @Query(value = "CALL SelectCongViecByTenCViec(:tenCV)", nativeQuery = true)
    List<CONGVIEC> GetByTenCViec(String tenCV); 

    @Query(value = "CALL GetCongViecByDiachiId(:diachiId)", nativeQuery = true)
    List<CONGVIEC> getCongViecByDiachiId(@Param("diachiId") int diachiId);

//     @Procedure(name = "checkUngTuyen")
//     List<CongViecResult> checkUngTuyen(@Param("user_id") Long user_id);

}
