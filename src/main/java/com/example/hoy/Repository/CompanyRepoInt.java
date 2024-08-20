package com.example.hoy.Repository;

import com.example.hoy.entity.CompanyEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepoInt extends JpaRepository<CompanyEntity, String> {

    CompanyEntity findByCompanySwiftCode(String swift);
}
