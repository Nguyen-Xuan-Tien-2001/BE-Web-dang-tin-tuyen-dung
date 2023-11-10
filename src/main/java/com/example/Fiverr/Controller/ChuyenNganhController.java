package com.example.Fiverr.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fiverr.DTO.CtyChuyenNganhRequest;
import com.example.Fiverr.DTO.DetailCongTyRequest;
import com.example.Fiverr.Model.CHUYENNGANH;
import com.example.Fiverr.Model.CONGTY;
import com.example.Fiverr.Model.DIACHI;
import com.example.Fiverr.Response.APIResponse;
import com.example.Fiverr.Service.ChuyenNganhService;

@RestController
@RequestMapping("/ChuyenNganh")
public class ChuyenNganhController {
    @Autowired
    private ChuyenNganhService chuyenNganhService;

    @GetMapping("/getall")
    public APIResponse getAllChuyenNganh() {
        List<CHUYENNGANH> ChuyenNganhlist = chuyenNganhService.getAllchuyennganh();
        APIResponse response = new APIResponse(true, ChuyenNganhlist, "lấy danh sach Chuyen Nganh thành công");
        return response;
    }
    @GetMapping("/congtychuyennganh")
    public APIResponse getCongTyChuyenNganh() {
        // return chuyenNganhService.getCongTyChuyenNganh();
        
        APIResponse response = new APIResponse(true, chuyenNganhService.getCongTyChuyenNganh(), "lay chuyên ngành thanh cong");
        return response;
    }
    // @PostMapping("/themchuyenNganh")
    // public APIResponse createCN(@RequestBody CHUYENNGANH chuyennganh){
    //     chuyenNganhService.createChuyenNganh(chuyennganh);
    //     APIResponse response = new APIResponse(true, chuyennganh, "them chuyên ngành thanh cong");
    //     return response;
    // }

    @PostMapping("/them")
    public APIResponse addCongTyAndChuyenNganh(@RequestBody CtyChuyenNganhRequest request) {
        chuyenNganhService.addCongTyAndChuyenNganh(request.getCongTy(), request.getChuyenNganh(), request.getChiNhanh());
        APIResponse response = new APIResponse(true, request, "them chuyên ngành thanh cong");
        return response;
    }
}
