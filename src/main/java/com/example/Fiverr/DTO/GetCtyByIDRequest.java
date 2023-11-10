package com.example.Fiverr.DTO;

import java.math.BigInteger;

import lombok.Data;

@Data
public class GetCtyByIDRequest {
    private BigInteger id;
    private String tenCty;
    private String tenDC;
}
