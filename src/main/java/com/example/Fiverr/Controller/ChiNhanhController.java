package com.example.Fiverr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fiverr.Model.CHINHANH;
import com.example.Fiverr.Model.CONGTY;
import com.example.Fiverr.Model.DIACHI;
import com.example.Fiverr.Model.Users;
import com.example.Fiverr.Response.APIResponse;
import com.example.Fiverr.Service.ChiNhanhService;

@RestController
@RequestMapping("/ChiNhanh")
public class ChiNhanhController {
    @Autowired
    private ChiNhanhService chiNhanhService;

    @GetMapping("/tenCtyByDiachi")
    public APIResponse getTenCtyByDiachi(@RequestParam Long diachiId) {
        if(chiNhanhService.getTenCtyByDiachi(diachiId).isEmpty()){
            APIResponse response = new APIResponse(false, null, "khong co dia chi hoac cong ty tai dia chi nay");
            return response;
        }
        APIResponse response = new APIResponse(true, chiNhanhService.getTenCtyByDiachi(diachiId), "Danh sach cong ty");
        return response;
        // return chiNhanhService.getTenCtyByDiachi(diachiId);
    }

    @GetMapping("/getall")
    public APIResponse getAllUsers() {
        List<CHINHANH> ChiNhanhlist = chiNhanhService.getAllchinhanh();
        APIResponse response = new APIResponse(true, ChiNhanhlist, "danh sach chi nhanh đã được lấy thành công");
        return response;
    }

    @GetMapping("/getByIdCty/{ctyId}")
    public APIResponse getChinhanhByIdCty(@PathVariable Long ctyId) {
        List<CHINHANH> ChiNhanhlist = chiNhanhService.getchinhanhByIdKho(ctyId);
        APIResponse response = new APIResponse(true, ChiNhanhlist, "danh sach chi nhanh đã được lấy thành công");
        return response;
    }
}
