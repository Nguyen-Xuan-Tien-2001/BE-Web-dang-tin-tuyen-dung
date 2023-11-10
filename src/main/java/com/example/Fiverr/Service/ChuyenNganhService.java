package com.example.Fiverr.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Fiverr.DTO.DetailCongTyRequest;
import com.example.Fiverr.Model.CHINHANH;
import com.example.Fiverr.Model.CHUYENNGANH;
import com.example.Fiverr.Model.CONGTY;
import com.example.Fiverr.Model.DIACHI;
import com.example.Fiverr.Repository.ChiNhanhCtyRepository;
import com.example.Fiverr.Repository.ChuyenNganhRepository;
import com.example.Fiverr.Repository.CongTyRepository;

@Service
public class ChuyenNganhService {
    @Autowired
    private ChuyenNganhRepository chuyenNganhRepo;

    @Autowired
    private CongTyRepository congTyRepository;

    @Autowired
    private ChiNhanhCtyRepository chinhanhRepository;


    public CHUYENNGANH createChuyenNganh(CHUYENNGANH chuyennganh) {
        return chuyenNganhRepo.save(chuyennganh);
    }

    public List<CHUYENNGANH> getAllchuyennganh() {
        return (List<CHUYENNGANH>) chuyenNganhRepo.findAll();
    }
    public List<DetailCongTyRequest> getCongTyChuyenNganh() {
        List<Object[]> resultList = congTyRepository.getCongTyChuyenNganh();
        List<DetailCongTyRequest> detailCongTyRequests = new ArrayList<>();

        for (Object[] result : resultList) {
            DetailCongTyRequest detailCongTyRequest = new DetailCongTyRequest();
            detailCongTyRequest.setTenCTY((String) result[0]);
            detailCongTyRequest.setTenChuyenNganh((String) result[1]);
            detailCongTyRequest.setTenDC((String) result[2]);
            detailCongTyRequests.add(detailCongTyRequest);
        }

        return detailCongTyRequests;
    }

    public CHUYENNGANH getDChuyenNganhById(Long id) {
        return chuyenNganhRepo.findById(id).orElse(null);
    }

    public void deleteChuyenNganh(Long id){
        chuyenNganhRepo.deleteById(id);
    }

    @Transactional
    public void addCongTyAndChuyenNganh(CONGTY congTy, CHUYENNGANH chuyenNganh, CHINHANH chinhanh) {
        // Lưu công ty
        congTyRepository.save(congTy);
        
        // Thiết lập khóa ngoại công ty cho chuyên ngành
        chuyenNganh.setCongty(congTy);
        chuyenNganhRepo.save(chuyenNganh);
        chinhanh.setCongty(congTy);
        chinhanhRepository.save(chinhanh);
    }
}
