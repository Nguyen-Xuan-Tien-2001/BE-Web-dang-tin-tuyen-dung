package com.example.Fiverr.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Fiverr.Model.DIACHI;
import com.example.Fiverr.Repository.DiaChiRepository;

@Service
public class DiaChiService {
    @Autowired
    private DiaChiRepository diachirepo;

    public DIACHI createDC(DIACHI diachi) {
        return diachirepo.save(diachi);
    }

    public List<DIACHI> getAllDC() {
        return (List<DIACHI>) diachirepo.findAll();
    }

    public DIACHI getDCById(Long id) {
        return diachirepo.findById(id).orElse(null);
    }

    public void deleteDC(Long id){
        diachirepo.deleteById(id);
    }

}
