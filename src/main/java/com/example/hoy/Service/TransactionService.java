package com.example.hoy.Service;

import com.example.hoy.Repository.TransactionRepoInt;
import com.example.hoy.entity.CompanyEntity;
import com.example.hoy.entity.TransactionEntity;
import com.example.hoy.model.dto.TransactionDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class TransactionService implements TransactionServiceInt {

    private final TransactionRepoInt transactionRepo;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public TransactionDto saveTransaction(TransactionDto transactionDto , CompanyEntity companyEntity) {
        // Map DTO to Entity
        TransactionEntity transactionEntity = modelMapper.map(transactionDto, TransactionEntity.class);

        transactionEntity.setCompanyEntity(companyEntity);
        // Save the entity
        TransactionEntity savedEntity = transactionRepo.save(transactionEntity);

        // Map back to DTO and return
        return modelMapper.map(savedEntity, TransactionDto.class);
    }
}