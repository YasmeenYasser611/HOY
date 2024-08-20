package com.example.hoy.Service;

import com.example.hoy.entity.CompanyEntity;
import com.example.hoy.model.dto.TransactionDto;

public interface TransactionServiceInt {
    public TransactionDto saveTransaction(TransactionDto transactionDto , CompanyEntity companyEntity);
}
