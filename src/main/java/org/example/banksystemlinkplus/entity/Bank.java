package org.example.banksystemlinkplus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private double transactionfee;
    private double transferamount;
    private double transactionflatfee;
    private double transactionpercentfee;

    @OneToMany(mappedBy = "bank",cascade = CascadeType.ALL)
    private List<Account> accounts;

    public Bank(String bankA, double v, double v1, double v2, double v3) {
        this.name = bankA;
        this.transactionfee = v;
        this.transferamount = v1;
        this.transactionflatfee = v2;
        this.transactionpercentfee = v3;
    }
}
