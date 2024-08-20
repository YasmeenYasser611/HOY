package com.example.hoy.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyEntity {
    @Id
    @Column(name = "swift_code")
    private String companySwiftCode;

    @Column(name = "name")
    private String companyName;

    @Column(name = "url")
    private String companyUrl;

    @Column(name = "address")
    private String companyAddress;

    @Column(name = "balance")
    private Long companyBalance;

    @Column(name = "description")
    private String companyDescription;
}
