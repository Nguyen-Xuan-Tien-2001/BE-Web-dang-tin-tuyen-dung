package com.example.Fiverr.DTO;

import java.math.BigInteger;

import lombok.Data;

@Data
public class CongViecCtyRequest {
    private BigInteger congviecId;
    private String tenCty;
    private String tenCV;
    private String tenChuyenNganh;
}
