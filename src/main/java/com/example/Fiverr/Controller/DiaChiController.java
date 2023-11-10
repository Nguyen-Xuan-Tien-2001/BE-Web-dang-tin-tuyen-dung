package com.example.Fiverr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fiverr.Model.DIACHI;
import com.example.Fiverr.Response.APIResponse;
import com.example.Fiverr.Service.DiaChiService;

@RestController
@RequestMapping("/diachi")
public class DiaChiController {
    @Autowired
    private DiaChiService diachiService;

    @GetMapping("/getall")
    public APIResponse getAllDC() {
        List<DIACHI> DiaChilist = diachiService.getAllDC();
        APIResponse response = new APIResponse(true, DiaChilist, "lấy danh sach dia chỉ thành công");
        return response;
    }

    @PostMapping("/them")
    public APIResponse createDC(@RequestBody DIACHI diachi){
        diachiService.createDC(diachi);
        APIResponse response = new APIResponse(true, diachi, "them dia chi thanh cong");
        return response;
    }
    @DeleteMapping("/xoa/{id}")
    public APIResponse deleteDC(@PathVariable Long id){
        diachiService.deleteDC(id);
        List<DIACHI> DiaChilist = diachiService.getAllDC();
        APIResponse response = new APIResponse(true, DiaChilist, "Xoa thành công");
        return response;
    }
    // @PutMapping("/sua/{id}")
    // public APIResponse SuaDC(@PathVariable Long id, @RequestBody DIACHI diachi) {
    //     try{
    //     DIACHI UpdateDiaChi = diachiService.getDCById(id);
    //         if(UpdateDiaChi==null) {
    //             APIResponse response = new APIResponse(false, null, "DiaChi khong co");
    //             return response;
    //         }else {
    //             System.out.println("addddddddddddd" + UpdateDiaChi);
    //             if (diachi.getTenDC() != null) {
    //                 UpdateDiaChi.setTenDC(diachi.getTenDC());
    //             }
    //             System.out.println("cccccccccccccccccccc" + UpdateDiaChi);
    //             diachiService.updateDC(id, diachi);
    //             APIResponse response = new APIResponse(true, UpdateDiaChi, "ok");
    //             return response;
    //         }
    //     }
    //     catch (Exception e) {
    //         APIResponse response = new APIResponse(false, e, null);
    //         return response;
    //     }
// }
}
