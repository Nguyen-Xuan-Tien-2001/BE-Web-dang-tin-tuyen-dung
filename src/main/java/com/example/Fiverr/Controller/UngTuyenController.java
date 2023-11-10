package com.example.Fiverr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fiverr.Model.CV;
import com.example.Fiverr.Model.UNGTUYEN;
import com.example.Fiverr.Model.Users;
import com.example.Fiverr.Response.APIResponse;
import com.example.Fiverr.Service.UngTuyenService;

@RestController
@RequestMapping("/UngTuyen")
public class UngTuyenController {
    @Autowired
    private UngTuyenService ungTuyenService;

    @GetMapping("/getall")
    public APIResponse getAllUT() {
        List<UNGTUYEN> UTlist = ungTuyenService.getAllUT();
        APIResponse response = new APIResponse(true, UTlist, "lấy danh sach Ung Tuyen thành công");
        return response;
    }

    @GetMapping("/byCongviec")
    public APIResponse getUngtuyenByCongviec(@RequestParam Long congviecId) {
        if (ungTuyenService.findUngtuyenByCongviecId(congviecId).isEmpty()) {
            APIResponse response = new APIResponse(false, null, "khong co danh sach ung tuyen");
            return response;
        }
        APIResponse response = new APIResponse(true, ungTuyenService.findUngtuyenByCongviecId(congviecId),
                "lấy danh sach Ung Tuyen thành công");
        return response;
        // return ungTuyenService.findUngtuyenByCongviecId(congviecId);
    }

    @GetMapping("/byCV")
    public APIResponse getUngtuyenByCV(@RequestParam Long CVId) {
        if (ungTuyenService.findUngtuyenByCVId(CVId).isEmpty()) {
            APIResponse response = new APIResponse(false, null, "khong co danh sach ung tuyen");
            return response;
        }
        APIResponse response = new APIResponse(true, ungTuyenService.findUngtuyenByCVId(CVId),
                "lấy danh sach Ung Tuyen thành công");
        return response;
        // return ungTuyenService.findUngtuyenByCongviecId(congviecId);
    }

    @PostMapping("/them")
    public APIResponse createUT(@RequestBody UNGTUYEN ungtuyen) {
        boolean exists = ungTuyenService.existsWithMaCVAndMaCViec(ungtuyen.getCv().getId(),
                ungtuyen.getCongviec().getId());
        if (exists) {
            APIResponse response = new APIResponse(false, ungtuyen, "Ban da ung tuyen vi tri nay'");
            return response;
        }
        ungtuyen.setTrangThai("Đã nộp");
        ungTuyenService.createUT(ungtuyen);
        APIResponse response = new APIResponse(true, ungtuyen, "them cv thanh cong");
        return response;
    }

    @PutMapping("/sua/{id}")
    public APIResponse SuaUser(@PathVariable Long id, @RequestBody UNGTUYEN ungtuyen) {
        try {
            UNGTUYEN UpdateUT = ungTuyenService.getUTById(id);
            System.out.println("aaaaaaaaaaaaaa" + UpdateUT);
            if (UpdateUT == null) {
                APIResponse response = new APIResponse(false, null, "UT khong co");
                return response;
            } else {
                if (ungtuyen.getTrangThai() != null) {
                    UpdateUT.setTrangThai(ungtuyen.getTrangThai());
                }
                ungTuyenService.updateUT(id, UpdateUT);
                APIResponse response = new APIResponse(true, UpdateUT, "ok");
                return response;
            }
        } catch (Exception e) {
            APIResponse response = new APIResponse(false, e, null);
            return response;
        }

        // @GetMapping("/check-exists")
        // public String checkExists(
        // @RequestParam Long cvId,
        // @RequestParam Long congviecId
        // ) {
        // boolean exists = ungTuyenService.existsWithMaCVAndMaCViec(cvId, congviecId);
        // if (exists) {
        // return "Cặp giá trị đã tồn tại trong bảng UNGTUYEN.";
        // } else {
        // return "Cặp giá trị không tồn tại trong bảng UNGTUYEN.";
        // }
        // }
    }

    // lay danh sách ứng tuyển đủ điểu kiện điểm GPA
    @GetMapping("/dudiemGPA")
    public APIResponse getUngTuyenDuDiemGPA(@RequestParam Long congviecId) {
        return ungTuyenService.getUngTuyenDuDiemGPA(congviecId);
    }

    // lấy danh sách ứng tuyển đủ điều kiện điểm TOEIC
    @GetMapping("/dudiemTOEIC")
    public APIResponse getUngTuyenDuDiemTOEIC(@RequestParam Long congviecId) {
        return ungTuyenService.getUngTuyenDuDiemTOEIC(congviecId);
    }

    // lấy danh sách ứng viên đủ điều kiện cả điểm GPA và điểm TOEIC
    @GetMapping("/dudiemTOEICandGPA")
    public APIResponse getUngTuyenDuDiemTOEICandGPA(@RequestParam Long congviecId) {
        return ungTuyenService.getUngTuyenDuDiemTOEICandGPA(congviecId);
    }
}
