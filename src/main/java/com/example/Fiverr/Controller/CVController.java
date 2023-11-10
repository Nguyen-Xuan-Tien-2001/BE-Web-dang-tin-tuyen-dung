package com.example.Fiverr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fiverr.Model.CV;
import com.example.Fiverr.Response.APIResponse;
import com.example.Fiverr.Service.CVService;

@RestController
@RequestMapping("/CV")
public class CVController {
    @Autowired
    private CVService cvService;

    @GetMapping("/getall")
    public APIResponse getAllCV() {
        List<CV> CVlist = cvService.getAllCV();
        APIResponse response = new APIResponse(true, CVlist, "lấy danh sach cv thành công");
        return response;
    }

    @PostMapping("/them")
    public APIResponse createCV(@RequestBody CV cv) {
        cvService.createCV(cv);
        APIResponse response = new APIResponse(true, cv, "them cv thanh cong");
        return response;
    }

    @DeleteMapping("/xoa/{id}")
    public APIResponse deleteCV(@PathVariable Long id) {
        cvService.deleteCV(id);
        APIResponse response = new APIResponse(true, null, "Xoa thành công");
        return response;
    }

    @GetMapping("/{userId}")
    public APIResponse getAllCVsByUserId(@PathVariable Long userId, CV cv) {
        List<CV> cvs = cvService.getAllCVByUserId(userId);
        if (cvs.isEmpty()) {
            APIResponse response = new APIResponse(false, null, "get cv user");
            return response;
        }
        APIResponse response = new APIResponse(true, cvs, "get cv user");
        return response;
        // return ResponseEntity.ok(cvs);
    }

    @PutMapping("/sua/{id}")
    public APIResponse suaCV(@PathVariable long id, @RequestBody CV cv) {
        try {
            CV updateCV = cvService.getCVById(id);
            if (updateCV == null) {
                APIResponse response = new APIResponse(false, null, "Bạn chưa có CV");
                return response;
            } else {
                if (cv.getHo() != null) {
                    updateCV.setHo(cv.getHo());
                }
                if (cv.getTen() != null) {
                    updateCV.setTen(cv.getTen());
                }
                if (cv.getCCCD() != null) {
                    updateCV.setCCCD(cv.getCCCD());
                }
                if (cv.getChuyenMon() != null) {
                    updateCV.setChuyenMon(cv.getChuyenMon());
                }
                if (cv.getDiaChi() != null) {
                    updateCV.setDiaChi(cv.getDiaChi());
                }
                if (cv.getHocVan() != null) {
                    updateCV.setHocVan(cv.getHocVan());
                }
                if (cv.getKyNangMem() != null) {
                    updateCV.setKyNangMem(cv.getKyNangMem());
                }
                if (cv.getMoTa() != null) {
                    updateCV.setMoTa(cv.getMoTa());
                }
                if (cv.getNgaySinh() != null) {
                    updateCV.setNgaySinh(cv.getNgaySinh());
                }
                if (cv.getSoDT() != null) {
                    updateCV.setSoDT(cv.getSoDT());
                }
                if (cv.getImage_url() != null) {
                    updateCV.setImage_url(cv.getImage_url());
                }
                if (cv.getEmail() != null) {
                    updateCV.setEmail(cv.getEmail());
                }
                if (cv.getVitriUngTuyen() != null) {
                    updateCV.setVitriUngTuyen(cv.getVitriUngTuyen());
                }
                if (cv.getDiemGPA() != null) {
                    updateCV.setDiemGPA(cv.getDiemGPA());
                }
                if (cv.getDiemTOEIC() != null) {
                    updateCV.setDiemTOEIC(cv.getDiemTOEIC());
                }
                cvService.updateCV(id, updateCV);
                APIResponse response = new APIResponse(true, updateCV, "UPDATE CV SUCCESS");
                return response;
            }
        } catch (Exception e) {
            APIResponse response = new APIResponse(false, e, null);
            return response;
        }
    }

    @GetMapping("/UngTuyen")
    public APIResponse getCVByUngTuyen(@RequestParam Long ungtuyenId)
    {
        return cvService.getCVByUngTuyen(ungtuyenId);
    }
}
