package com.example.Fiverr.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Fiverr.Model.CONGVIEC;
import com.example.Fiverr.Model.UNGTUYEN;

@Repository
public interface UngTuyenRepository extends JpaRepository<UNGTUYEN, Long>{
    boolean existsByCvIdAndCongviecId(Long cvId, Long congviecId);
    List<UNGTUYEN> findByCongviec_Id(Long congviecId);
    List<UNGTUYEN> findByCvId(Long cvId);

    @Query(value = "CALL GetListUngVienDuDiemGPA(:congviecId)", nativeQuery = true)
    List<UNGTUYEN> findUngTuyenDuDiemGPA(Long congviecId);

    // lấy danh sách ứng tuyển đủ điểm TOEIC
    @Query(value = "CALL GetListUngVienDuDiemTOEIC(:congviecId)", nativeQuery = true)
    List<UNGTUYEN> findUngTuyenDuDiemTOEIC(Long congviecId);

    @Query(value = "CALL GetListUngVienDuDiemTOEICandGPA(:congviecId)", nativeQuery = true)
    List<UNGTUYEN> findUngTuyenDuDiemTOEICandGPA(Long congviecId);
}
