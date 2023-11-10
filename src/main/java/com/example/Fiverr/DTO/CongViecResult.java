package com.example.Fiverr.DTO;

import lombok.Data;

@Data
public class CongViecResult {
        private Long cong_viec_id; // Match the column name returned by the stored procedure
        private String ket_qua;

}
