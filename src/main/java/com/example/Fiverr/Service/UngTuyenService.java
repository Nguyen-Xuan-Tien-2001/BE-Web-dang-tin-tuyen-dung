package com.example.Fiverr.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Fiverr.Model.CV;
import com.example.Fiverr.Model.DIACHI;
import com.example.Fiverr.Model.UNGTUYEN;
import com.example.Fiverr.Repository.UngTuyenRepository;
import com.example.Fiverr.Response.APIResponse;

@Service
public class UngTuyenService {
    @Autowired
    private UngTuyenRepository ungtuyenRepo;

    public boolean existsWithMaCVAndMaCViec(Long cvId, Long congviecId) {
        return ungtuyenRepo.existsByCvIdAndCongviecId(cvId, congviecId);
    }

    public UNGTUYEN createUT(UNGTUYEN ungtuyen) {
        return ungtuyenRepo.save(ungtuyen);
    }

    public List<UNGTUYEN> getAllUT() {
        return (List<UNGTUYEN>) ungtuyenRepo.findAll();
    }

    public UNGTUYEN getUTById(Long id) {
        return ungtuyenRepo.findById(id).orElse(null);
    }
    public UNGTUYEN updateUT(Long id, UNGTUYEN ungtuyen) {
        return ungtuyenRepo.save(ungtuyen);
    }

    public void deleteUT(Long id){
        ungtuyenRepo.deleteById(id);
    }
    
    public List<UNGTUYEN> findUngtuyenByCongviecId(Long congviecId) {
        return ungtuyenRepo.findByCongviec_Id(congviecId);
    }

    public List<UNGTUYEN> findUngtuyenByCVId(Long CVId) {
        return ungtuyenRepo.findByCvId(CVId);
    }

     // lay danh sách ứng tuyển đủ điểu kiện điểm GPA
    public APIResponse getUngTuyenDuDiemGPA(Long congviecId)
    {
        List<UNGTUYEN>  ungtuyenList = ungtuyenRepo.findUngTuyenDuDiemGPA(congviecId);
        if(ungtuyenList.isEmpty())
        {
            APIResponse response = new APIResponse(false, null, "Không có ứng tuyển đủ điều kiện điểm GPA");
            return response;
        }
        APIResponse response = new APIResponse(true, ungtuyenList, "Danh sách úng tuyển đủ điều kiện điểm GPA");
        return response;
    }

    // lấy danh sách ứng tuyển đủ điều kiện điểm TOEIC
    public APIResponse getUngTuyenDuDiemTOEIC(Long congviecId)
    {
        List<UNGTUYEN>  ungtuyenList = ungtuyenRepo.findUngTuyenDuDiemTOEIC(congviecId);
        if(ungtuyenList.isEmpty())
        {
            APIResponse response = new APIResponse(false, null, "Không có ứng tuyển đủ điều kiện điểm TOEIC");
            return response;
        }
        APIResponse response = new APIResponse(true, ungtuyenList, "Danh sách úng tuyển đủ điều kiện điểm TOEIC");
        return response;
    }

    // lấy danh sách ứng tuyển đủ cả điểm TOEIC và điểm GPA
    public APIResponse getUngTuyenDuDiemTOEICandGPA(Long congviecId)
    {
        List<UNGTUYEN>  ungtuyenList = ungtuyenRepo.findUngTuyenDuDiemTOEICandGPA(congviecId);
        if(ungtuyenList.isEmpty())
        {
            APIResponse response = new APIResponse(false, null, "Không có ứng tuyển đủ điều kiện cả điểm GPA và điểm TOEIC");
            return response;
        }
        APIResponse response = new APIResponse(true, ungtuyenList, "Danh sách úng tuyển đủ điều kiện cả điểm GPA và điểm TOEIC");
        return response;
    }
}
