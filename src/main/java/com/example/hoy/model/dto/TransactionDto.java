package com.example.hoy.model.dto;

import com.example.hoy.entity.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long amount;
    private String status;
    private String companySwiftCode_second;
    private String companySwiftCode_first;  //controllerMinistry

}
