package com.example.Fiverr.Controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fiverr.DTO.CongViecCtyRequest;
import com.example.Fiverr.DTO.CongViecResult;
import com.example.Fiverr.DTO.CtyChuyenNganhRequest;
import com.example.Fiverr.DTO.GetCtyByIDRequest;
import com.example.Fiverr.Model.CONGVIEC;
import com.example.Fiverr.Model.CV;
import com.example.Fiverr.Response.APIResponse;
import com.example.Fiverr.Service.CongViecService;

@RestController
@RequestMapping("/CongViec")
public class CongviecController {

    @Autowired
    private CongViecService congviecService;

    @GetMapping("/{userId}")
    public APIResponse getTenCtyByUserId(@PathVariable Long userId) {
        // List<GetCtyByIDRequest> result = congviecService.getTenCtyByUserId(userId);
        // return new ResponseEntity<>(result, HttpStatus.OK);
        APIResponse response = new APIResponse(true, congviecService.getTenCtyByUserId(userId), "lay cong ty thanh cong");
        return response;
    }

    @GetMapping("/getcongviec/{CongviecId}")
    public APIResponse getCongviecById(@PathVariable Long CongviecId) {
        // List<GetCtyByIDRequest> result = congviecService.getTenCtyByUserId(userId);
        // return new ResponseEntity<>(result, HttpStatus.OK);
        APIResponse response = new APIResponse(true, congviecService.getCongViecById(CongviecId), "lay cong viec by id thanh cong");
        return response;
    }

    @GetMapping("/getall")
    public APIResponse getAllCongViec() {
        List<CONGVIEC> CongVieclist = congviecService.getAllCongViec();
        APIResponse response = new APIResponse(true, CongVieclist, "lấy danh sach cong viec thành công");
        return response;
    }

    @PostMapping("/them")
    public APIResponse createCongViec(@RequestBody CONGVIEC congviec) {
        Date currentDate = new Date(System.currentTimeMillis());
        congviec.setNgayDang(currentDate);
        congviecService.createCongViec(congviec);
        APIResponse response = new APIResponse(true, congviec, "them cong viec thanh cong");
        return response;
    }

    @GetMapping("/byChinhanh")
    public APIResponse getCongViecCTYByChinhanh(@RequestParam Long chinhanhId) {
        List<Object[]> results = congviecService.getCongViecCTY(chinhanhId);

        List<CongViecCtyRequest> dtos = new ArrayList<>();
        for (Object[] result : results) {
            CongViecCtyRequest dto = new CongViecCtyRequest();
            dto.setCongviecId((BigInteger) result[0]);
            dto.setTenCty((String) result[1]);
            dto.setTenCV((String) result[2]);
            dto.setTenChuyenNganh((String) result[3]);
            dtos.add(dto);
        }
        if (dtos.isEmpty()) {
            APIResponse response = new APIResponse(false, null, "cong ty tren khong co cong viec");
            return response;
        }
        APIResponse response = new APIResponse(true, dtos, "danh sach cong viec tai cong ty");
        return response;
        // return dtos;
    }

    @DeleteMapping("/xoa/{id}")
    public APIResponse deleteCongViec(@PathVariable Long id) {
        congviecService.deleteCongViec(id);
        List<CONGVIEC> CongVieclist = congviecService.getAllCongViec();
        APIResponse response = new APIResponse(true, CongVieclist, "Xoa thành công");
        return response;
    }


    // @GetMapping("/ungtuyen/{userId}")
    // @Transactional(readOnly = true) // Add this annotation
    // public ResponseEntity<List<CongViecResult>> getUngTuyenResults(@PathVariable Long userId) {
    //     List<CongViecResult> results = congviecService.getUngTuyenResults(userId);
    //     return new ResponseEntity<>(results, HttpStatus.OK);
    // }

    @GetMapping("/Luong")
    public APIResponse getCongViecByLuong(
            @RequestParam(name = "minSalary") BigDecimal minSalary,
            @RequestParam(name = "maxSalary") BigDecimal maxSalary
    ) {
        if (congviecService.getCongviecByLuong(minSalary, maxSalary).isEmpty()) {
            APIResponse response = new APIResponse(false, null, "Khong co cong viec trong khoang luong nay");
            return response;
        }
        APIResponse response = new APIResponse(true, congviecService.getCongviecByLuong(minSalary, maxSalary), "Cong viec trong khoang luong tren");
        return response;
        // return congviecService.getCongviecByLuong(minSalary, maxSalary);
    }

    // lấy danh sách công việc theo tên chuyên ngành
    @GetMapping("/ChuyenNganh")
    public APIResponse getCongViecByChuyenNganh(@RequestParam(name = "tenChuyenNganh") String tenChuyenNganh)
    {
        if(congviecService.getCongViecByChuyenNganh(tenChuyenNganh).isEmpty())
        {
            APIResponse response = new APIResponse(false, null, "Khong co cong viec theo chuyen nganh yeu cau");
            return response;
        }
        APIResponse response = new APIResponse(true, congviecService.getCongViecByChuyenNganh(tenChuyenNganh), "Danh sach cong viec theo chuyen nganh");
        return response;
    }

    // lấy danh sách công việc theo tên công việc, địa chỉ, tên Chuyên Ngành, mức lương
    @GetMapping("/by-tenCV-diachi-tenChuyenNganh-luong")
    public APIResponse getCongViecByTenAndDiaChiAndChuyenNganhAndLuong(@RequestParam(name = "tenCV") String tenCV,
                                                                       @RequestParam(name = "idDiaChi") Long diachiId,
                                                                       @RequestParam(name = "tenChuyenNganh") String tenChuyenNganh,
                                                                       @RequestParam(name = "minLuong") BigDecimal minLuong,
                                                                       @RequestParam(name = "maxLuong") BigDecimal maxLuong)
    {
        if(congviecService.getCongViecByTenAndDiaChiAndChuyenNganhAndLuong(tenCV, diachiId, tenChuyenNganh, minLuong, maxLuong).isEmpty())
        {
            APIResponse response = new APIResponse(false, null, "NO JOBS MATCHING THE SEARCH INFORMATION!");
            return response;
        }
        APIResponse response = new APIResponse(true, congviecService.getCongViecByTenAndDiaChiAndChuyenNganhAndLuong(tenCV, diachiId, tenChuyenNganh, minLuong, maxLuong), "SUITABLE JOB LIST!");
        return response;
    }


    @GetMapping("/congviec-by-tencviec")
    public APIResponse getCongViecByTenCViec(@RequestParam("tenCViec") String tenCViec) {
        if (congviecService.getCongViecByTenCViec(tenCViec).isEmpty()) {
            APIResponse response = new APIResponse(false, null, "Không tìm thấy công việc theo tên");
            return response;
        }
        APIResponse response = new APIResponse(true, congviecService.getCongViecByTenCViec(tenCViec), "Danh sách công việc theo tên");
        return response;
        // return congviecService.getCongViecByTenCViec(tenCViec);
    }

    @GetMapping("/congviec-by-diachi")
    public APIResponse getCongViecByDiachiId(@RequestParam("diachiId") int diachiId) {
        if (congviecService.getCongViecByDiaChi(diachiId).isEmpty()) {
            APIResponse response = new APIResponse(false, null, "Khong co cong viec tai dia chi nay");
            return response;
        }
        APIResponse response = new APIResponse(true, congviecService.getCongViecByDiaChi(diachiId), "Danh sach cong viec theo dia chi");
        return response;
        // return congviecService.getCongViecByDiaChi(diachiId);
    }

    @PutMapping("/sua/{id}")
    public APIResponse suaCV(@PathVariable long id, @RequestBody CONGVIEC congviec) {
        try {
            CONGVIEC updateCongViec = congviecService.getCongViecById(id);
            if (updateCongViec == null) {
                APIResponse response = new APIResponse(false, null, "Cong Viec chua có");
                return response;
            } else {
                if (congviec.getYeuCau() != null) {
                    updateCongViec.setYeuCau(congviec.getYeuCau());
                }
                if (congviec.getLuong() != 0) {
                    updateCongViec.setLuong(congviec.getLuong());
                }
                if (congviec.getMoTa() != null) {
                    updateCongViec.setMoTa(congviec.getMoTa());
                }
                if (congviec.getTenCViec() != null) {
                    updateCongViec.setTenCViec(congviec.getTenCViec());
                }
                if (congviec.getHanUngTuyen() != null) {
                    updateCongViec.setHanUngTuyen(congviec.getHanUngTuyen());
                }
                if (congviec.getSoluong() != 0) {
                    updateCongViec.setSoluong(congviec.getSoluong());
                }
                congviecService.updateCongViec(id, updateCongViec);
                APIResponse response = new APIResponse(true, updateCongViec, "ok");
                return response;
            }
        } catch (Exception e) {
            APIResponse response = new APIResponse(false, e, null);
            return response;
        }
    }
}
