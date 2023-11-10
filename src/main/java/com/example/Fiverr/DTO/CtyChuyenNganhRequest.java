package com.example.Fiverr.DTO;

import com.example.Fiverr.Model.CHINHANH;
import com.example.Fiverr.Model.CHUYENNGANH;
import com.example.Fiverr.Model.CONGTY;
import com.example.Fiverr.Model.DIACHI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CtyChuyenNganhRequest {
    private CONGTY congTy;
    private CHUYENNGANH chuyenNganh;
    private CHINHANH chiNhanh;
}
