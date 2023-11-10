package com.example.Fiverr.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Fiverr.DTO.GetCtyByIDRequest;
import com.example.Fiverr.Model.CONGVIEC;
import com.example.Fiverr.Repository.CongViecRepository;

@Service
public class CongViecService {
    @Autowired
    private CongViecRepository congviecrepo;

    public List<CONGVIEC> getAllCongViec() {
        return (List<CONGVIEC>) congviecrepo.findAll();
    }
    public CONGVIEC createCongViec(CONGVIEC congviec) {
        return congviecrepo.save(congviec);
    }

    public CONGVIEC getCongViecById(Long id) {
        return congviecrepo.findById(id).orElse(null);
    }

    public void deleteCongViec(Long id){
        congviecrepo.deleteById(id);
    }

    public CONGVIEC updateCongViec(Long id, CONGVIEC congviec) {
        return congviecrepo.save(congviec);
    }

    public List<GetCtyByIDRequest> getTenCtyByUserId(Long userId) {
        List<Object[]> resultList = congviecrepo.getTenCtyByUserId(userId);
        List<GetCtyByIDRequest> responseList = new ArrayList<>();
        for (Object[] result : resultList) {
            GetCtyByIDRequest request = new GetCtyByIDRequest();
            request.setId((BigInteger ) result[0]);
            request.setTenCty((String) result[1]);
            request.setTenDC((String) result[2]);
            responseList.add(request);
        }
        return responseList;
    }
    public List<Object[]> getCongViecCTY(Long chinhanhId) {
        return congviecrepo.GetCongViecCTY(chinhanhId);
    }
    public List<CONGVIEC> getCongviecByLuong(BigDecimal minSalary, BigDecimal maxSalary) {
        return congviecrepo.getCongViecByLuongRange(minSalary, maxSalary);
    }

    // lấy danh sách công việc theo tên chuyên ngành
    public List<CONGVIEC> getCongViecByChuyenNganh(String tenChuyenNganh)
    {
        return congviecrepo.getCongViecByChuyenNganh(tenChuyenNganh);
    }

    // lấy danh sách công việc theo tên công việc, địa chỉ, tên Chuyên Ngành, mức lương
    public List<CONGVIEC> getCongViecByTenAndDiaChiAndChuyenNganhAndLuong(String tenCongViec, Long diachiId, String tenChuyenNganh, BigDecimal minLuong, BigDecimal maxLuong)
    {
        return congviecrepo.getCongViecByTenAndDiaChiAndChuyenNganhAndLuong(tenCongViec, diachiId, tenChuyenNganh, minLuong, maxLuong);
    }

    public List<CONGVIEC> getCongViecByTenCViec(String tenCViec) {
        return congviecrepo.GetByTenCViec(tenCViec);
    }

    public List<CONGVIEC> getCongViecByDiaChi(int diachiId) { // Use lowercase "g" in method name
        return congviecrepo.getCongViecByDiachiId(diachiId);
    }

    // public List<CongViecResult> getUngTuyenResults(Long userId) {
    //     return congviecrepo.checkUngTuyen(userId);
    // }

    // tim kiem cong viec theo ten, luong va dia chi

    public void updateJobStatusBasedOnExpiry() {
        List<CONGVIEC> jobs = congviecrepo.findAll();

        LocalDate currentDate = LocalDate.now();

        for (CONGVIEC job : jobs) {
            if (currentDate.isAfter(job.getHanUngTuyen().toLocalDate())) {
                job.setTrangThai("Hết hạn");
                congviecrepo.save(job);
            }
        }
    }
}