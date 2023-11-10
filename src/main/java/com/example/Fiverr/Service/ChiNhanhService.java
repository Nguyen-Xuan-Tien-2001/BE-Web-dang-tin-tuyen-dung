package com.example.Fiverr.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Fiverr.Model.CHINHANH;
import com.example.Fiverr.Model.CHUYENNGANH;
import com.example.Fiverr.Model.CONGTY;
import com.example.Fiverr.Model.DIACHI;
import com.example.Fiverr.Repository.ChiNhanhCtyRepository;

@Service
public class ChiNhanhService {
    @Autowired
    private ChiNhanhCtyRepository chinhanhRepository;

    public List<String> getTenCtyByDiachi(Long diachiId) {
        List<Object[]> results = chinhanhRepository.GetTenCtyByDiachi(diachiId);
        return results.stream()
                      .map(result -> (String) result[0])
                      .collect(Collectors.toList());
    }
    
    public List<CHINHANH> getAllchinhanh() {
        return (List<CHINHANH>) chinhanhRepository.findAll();
    }

    public List<CHINHANH> getchinhanhByIdKho(Long congtyId) {
        return (List<CHINHANH>) chinhanhRepository.findByCongtyId(congtyId);
    }
}
