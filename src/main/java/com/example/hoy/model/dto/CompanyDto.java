package com.example.hoy.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private String companySwiftCode;
    private String companyName;
    private String companyUrl;
    private String companyAddress;
    private Long companyBalance;
    private String companyDescription;
}
