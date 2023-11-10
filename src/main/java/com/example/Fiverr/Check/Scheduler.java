package com.example.Fiverr.Check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.Fiverr.Service.CongViecService;

@Component
public class Scheduler {
    
    @Autowired
    private CongViecService congViecService;

//    @Scheduled(fixedRate = 5000)
//    public void updateJobStatus() {
//        congViecService.updateJobStatusBasedOnExpiry();
//    }
}
