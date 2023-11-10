package com.example.Fiverr.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Fiverr.Model.CV;
import com.example.Fiverr.Repository.CVRepository;
import com.example.Fiverr.Response.APIResponse;

@Service
public class CVService {
    @Autowired
    private CVRepository cvRepository;

    public CV createCV(CV cv) {
        return cvRepository.save(cv);
    }

    public List<CV> getAllCV() {
        return (List<CV>) cvRepository.findAll();
    }

    public CV getCVById(Long id) {
        return cvRepository.findById(id).orElse(null);
    }

    public void deleteCV(Long id){
        cvRepository.deleteById(id);
    }

    public CV updateCV(Long id, CV cv) {
        return cvRepository.save(cv);
    }

    public List<CV> getAllCVByUserId(Long userId) {
        return cvRepository.findByUserId(userId);
    }

    // lấy CV của 1 ứng tuyển
    public APIResponse getCVByUngTuyen(Long ungtuyenId)
    {
        CV cvUngTuyen = cvRepository.findCVByUngTuyenId(ungtuyenId);
        APIResponse response = new APIResponse(true, cvUngTuyen, "CV ứng viên");
        return response;
    }
}
