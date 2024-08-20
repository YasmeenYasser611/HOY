package com.example.hoy.Service;

import com.example.hoy.Repository.CompanyRepoInt;
import com.example.hoy.Repository.TransactionRepoInt;
import com.example.hoy.entity.CompanyEntity;
import com.example.hoy.entity.TransactionEntity;
import com.example.hoy.model.dto.CompanyDto;
import com.example.hoy.model.dto.TransactionDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService implements  CompanyServiceInt {
    private final CompanyRepoInt companyRepo;
    private final ModelMapper modelMapper;
    private final TransactionService transactionService;
    private final TransactionRepoInt transactionRepoInt;

    @Override
    @Transactional
    public CompanyDto saveCompany(CompanyDto companyDto) {
        CompanyEntity companyEntity = modelMapper.map(companyDto, CompanyEntity.class);
        CompanyEntity result = companyRepo.save(companyEntity);
        return modelMapper.map(result, CompanyDto.class);
    }

    @Transactional
    public boolean checkCompany(TransactionDto transactionDto) {
        // Fetch the company entity for the controller ministry (first SWIFT code)
        CompanyEntity controllerMinistryEntity = companyRepo.findByCompanySwiftCode(transactionDto.getCompanySwiftCode_first());

        // Fetch the company entity for the target bank (second SWIFT code)
        CompanyEntity targetBankEntity = companyRepo.findByCompanySwiftCode(transactionDto.getCompanySwiftCode_second());

        // Validate that both entities exist
        if (controllerMinistryEntity != null && targetBankEntity != null) {
            if (transactionDto.getStatus().equals("T")) { // Transfer
                // Deduct from the controller ministry entity's balance
                controllerMinistryEntity.setCompanyBalance(controllerMinistryEntity.getCompanyBalance() - transactionDto.getAmount());

                // Add to the target bank entity's balance
                targetBankEntity.setCompanyBalance(targetBankEntity.getCompanyBalance() + transactionDto.getAmount());

                // Save both entities
                companyRepo.save(controllerMinistryEntity);
                companyRepo.save(targetBankEntity);
            } else if (transactionDto.getStatus().equals("R")) { // Receive
                // Add to the controller ministry entity's balance
                controllerMinistryEntity.setCompanyBalance(controllerMinistryEntity.getCompanyBalance() + transactionDto.getAmount());

                // Deduct from the target bank entity's balance
                targetBankEntity.setCompanyBalance(targetBankEntity.getCompanyBalance() - transactionDto.getAmount());

                // Save both entities
                companyRepo.save(controllerMinistryEntity);
                companyRepo.save(targetBankEntity);
            }

            // Save the transaction record
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setAmount(transactionDto.getAmount());
            transactionEntity.setStatus(transactionDto.getStatus());
            transactionEntity.setCompanySwiftCodeFirst(transactionDto.getCompanySwiftCode_first());
            transactionEntity.setCompanyEntity(targetBankEntity); // Set the second SWIFT code

            transactionRepoInt.save(transactionEntity); // Save the transaction to the database

            return true;
        } else {
            return false; // Return false if either entity is not found
        }
    }
}




//    @Transactional
//    public boolean checkCompany(TransactionDto transactionDto) {
//        // Fetch the company entity for the controller ministry (first SWIFT code)
//        CompanyEntity controllerMinistryEntity = companyRepo.findByCompanySwiftCode(transactionDto.getCompanySwiftCode_first());
//
//        // Fetch the company entity for the target bank (second SWIFT code)
//        CompanyEntity targetBankEntity = companyRepo.findByCompanySwiftCode(transactionDto.getCompanySwiftCode_second());
//
//        // Validate that both entities exist
//        if (controllerMinistryEntity != null && targetBankEntity != null) {
//            if (transactionDto.getStatus().equals("T")) { // Transfer
//                // Deduct from the controller ministry entity's balance
//                controllerMinistryEntity.setCompanyBalance(controllerMinistryEntity.getCompanyBalance() - transactionDto.getAmount());
//
//                // Add to the target bank entity's balance
//                targetBankEntity.setCompanyBalance(targetBankEntity.getCompanyBalance() + transactionDto.getAmount());
//
//                // Save both entities
//                companyRepo.save(controllerMinistryEntity);
//                companyRepo.save(targetBankEntity);
//            } else if (transactionDto.getStatus().equals("R")) { // Receive
//                // Add to the controller ministry entity's balance
//                controllerMinistryEntity.setCompanyBalance(controllerMinistryEntity.getCompanyBalance() + transactionDto.getAmount());
//
//                // Deduct from the target bank entity's balance
//                targetBankEntity.setCompanyBalance(targetBankEntity.getCompanyBalance() - transactionDto.getAmount());
//
//                // Save both entities
//                companyRepo.save(controllerMinistryEntity);
//                companyRepo.save(targetBankEntity);
//            }
//
//            // Save the transaction record
//            transactionService.saveTransaction(transactionDto, targetBankEntity);
//
//            return true;
//        } else {
//            return false; // Return false if either entity is not found
//        }
//    }
//    @Transactional
//    public boolean checkCompany(TransactionDto transactionDto) {
//
//        CompanyEntity companyEntity = companyRepo.findByCompanySwiftCode(transactionDto.getCompanySwiftCode_second());
//
//        if (companyEntity!= null) {
//            if (transactionDto.getStatus().equals("T")) {
//                companyEntity.setCompanyBalance(companyEntity.getCompanyBalance()-transactionDto.getAmount());
//                companyRepo.save(companyEntity);
//                transactionService.saveTransaction(transactionDto,companyEntity);
//                return true;
//            }else{
//                companyEntity.setCompanyBalance(companyEntity.getCompanyBalance()+transactionDto.getAmount());
//                companyRepo.save(companyEntity);
//                transactionService.saveTransaction(transactionDto,companyEntity);
//                return true;
//            }
//
//
//        }
//        else {
//            return false;
//        }
//    }

//    public void saveCompany(CompanyDto companyDto) {
//        CompanyEntity company = new CompanyEntity();
//        company.setCompanyName(companyDto.getCompanyName());
//        company.setCompanyAddress(companyDto.getCompanyAddress());
//        // Map other fields as needed
//
//        companyRepo.save(company);  // Save to the database
//    }

//    public CompanyEntity save(CompanyDto companyDto) {
//        return companyRepo.save(companyDto);
//    }


