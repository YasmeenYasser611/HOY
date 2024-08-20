package com.example.hoy.Repository;

import com.example.hoy.entity.CompanyEntity;
import com.example.hoy.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepoInt extends JpaRepository<TransactionEntity,Long> {

}
