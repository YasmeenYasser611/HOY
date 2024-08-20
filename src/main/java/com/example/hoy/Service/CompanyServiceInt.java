package com.example.hoy.Service;

import com.example.hoy.model.dto.CompanyDto;
import com.example.hoy.model.dto.TransactionDto;

public interface CompanyServiceInt {
    public CompanyDto saveCompany(CompanyDto companyDto);
    public boolean checkCompany(TransactionDto transactionDto);
}
