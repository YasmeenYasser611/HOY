package com.example.hoy.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "type")
    private String status;

    @Column(name = "company_swift_code_first")
    private String companySwiftCodeFirst;

    @ManyToOne
    @JoinColumn(name = "company_swift_code_second")
    private CompanyEntity companyEntity;
}
